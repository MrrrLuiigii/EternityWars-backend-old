package com.eternitywars.api.DAL.Contexts.Friend;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContainerContext;
import com.eternitywars.api.Models.Enums.FriendStatus;
import com.eternitywars.api.Models.Friend;
import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelationshipContainerSqlContext implements IRelationshipContainerContext
{
    private DatabaseConnection dbc;

    public RelationshipContainerSqlContext(){
        dbc = new DatabaseConnection();
    }



    public boolean AddRelationship(User user, Friend friend)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "insert into `friend`(`user_one_id`, `user_two_id`, `status`) " +
                    "values(?, ?, ?);";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getUserId());
                pst.setInt(2, friend.getUserId());
                pst.setString(3, FriendStatus.Pending.toString());
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

    public boolean DeleteRelationship(User user, Friend friend)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "delete from `friend` " +
                    "where (`user_one_id` = ? and `user_two_id` = ? and `status` != 'Blocked') " +
                    "or (`user_two_id` = ? and `user_one_id` = ? and `status` != 'Blocked');";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getUserId());
                pst.setInt(2, friend.getUserId());
                pst.setInt(3, user.getUserId());
                pst.setInt(4, friend.getUserId());
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
            String query = "select `user_one_id`, `user_two_id`, `status` " +
                    "from friend " +
                    "where `user_one_id` = ? or `user_two_id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getUserId());
                pst.setInt(2, user.getUserId());

                try (ResultSet rs = pst.executeQuery())
                {
                    while (rs.next())
                    {
                        Relationship relationship = new Relationship();
                        relationship.setFriendOneId(rs.getInt("user_one_id"));
                        relationship.setFriendTwoId(rs.getInt("user_two_id"));
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
