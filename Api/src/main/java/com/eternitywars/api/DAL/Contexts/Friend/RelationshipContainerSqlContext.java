package com.eternitywars.api.DAL.Contexts.Friend;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContainerContext;
import com.eternitywars.api.Models.Enums.FriendStatus;
import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RelationshipContainerSqlContext implements IRelationshipContainerContext
{
    private DatabaseConnection dbc;

    public RelationshipContainerSqlContext(){
        dbc = new DatabaseConnection();
    }



    public RelationshipCollection GetRelationships(User user)
    {
        RelationshipCollection relationshipCollection = new RelationshipCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `user_one_id`, `user_two_id`, `status` " +
                    "from friend " +
                    "where `user_one_id` = ? or `user_two_id` = ?;";

            try (PreparedStatement cst = conn.prepareStatement(query))
            {
                cst.setInt(1, user.getUserId());

                try (ResultSet rs = cst.executeQuery())
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
