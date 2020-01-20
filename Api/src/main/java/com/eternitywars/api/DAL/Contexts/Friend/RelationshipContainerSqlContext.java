package com.eternitywars.api.DAL.Contexts.Friend;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.IDatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContainerContext;
import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.Enums.FriendStatus;
import com.eternitywars.api.Models.Friend;
import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelationshipContainerSqlContext implements IRelationshipContainerContext
{
    private IDatabaseConnection dbc;

    public RelationshipContainerSqlContext(DatabaseConnection dbc)
    {
        this.dbc = dbc;
    }

    public RelationshipContainerSqlContext(TestDatabaseConnection dbc)
    {
        this.dbc = dbc;
    }

    public boolean AddRelationship(Relationship relationship)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call RequestFriend(?, ?, ?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, relationship.getFriendOneId());
                cst.setInt(2, relationship.getFriendTwoId());
                cst.setString(3, FriendStatus.Pending.toString());
                cst.executeQuery();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public boolean DeleteRelationship(Relationship relationship)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "delete from `friend` " +
                    "where (`user_one_id` = ? and `user_two_id` = ? and `status` != 'Blocked') " +
                    "or (`user_two_id` = ? and `user_one_id` = ? and `status` != 'Blocked');";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, relationship.getFriendOneId());
                pst.setInt(2, relationship.getFriendTwoId());
                pst.setInt(3, relationship.getFriendOneId());
                pst.setInt(4, relationship.getFriendTwoId());
                pst.executeUpdate();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public RelationshipCollection GetRelationships(User user)
    {
        RelationshipCollection relationshipCollection = new RelationshipCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "SELECT " +
                        "ONE.`id` AS friend_one_id, " +
                        "ONE.`username` AS friend_one_username, " +
                        "ONE.`account_status` AS friend_one_account_status, " +
                        "TWO.`id` AS friend_two_id, " +
                        "TWO.`username` AS friend_two_username, " +
                        "TWO.`account_status` AS friend_two_account_status, " +
                        "F.`status` " +
                    "FROM `friend` AS F " +
                    "INNER JOIN `user` AS ONE " +
                    "ON F.`user_one_id` = ONE.`id` " +
                    "INNER JOIN `user` AS TWO " +
                    "ON F.`user_two_id` = TWO.`id`" +
                    "WHERE ONE.`id` = ? OR TWO.`id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getUserId());
                pst.setInt(2, user.getUserId());

                try (ResultSet rs = pst.executeQuery())
                {
                    while (rs.next())
                    {
                        Relationship relationship = new Relationship();

                        relationship.setFriendOneId(rs.getInt("friend_one_id"));
                        relationship.setFriendOneUsername(rs.getString("friend_one_username"));
                        relationship.setFriendOneAccountStatus(AccountStatus.valueOf(rs.getString("friend_one_account_status")));

                        relationship.setFriendTwoId(rs.getInt("friend_two_id"));
                        relationship.setFriendTwoUsername(rs.getString("friend_two_username"));
                        relationship.setFriendTwoAccountStatus(AccountStatus.valueOf(rs.getString("friend_two_account_status")));

                        relationship.setFriendStatus(FriendStatus.valueOf(rs.getString("status")));
                        relationshipCollection.getRelationships().add(relationship);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return relationshipCollection;
    }
}
