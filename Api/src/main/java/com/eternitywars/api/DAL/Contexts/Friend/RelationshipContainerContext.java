package com.eternitywars.api.DAL.Contexts.Friend;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.IRelationshipContainerContext;
import com.eternitywars.api.Models.Enums.FriendStatus;
import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.RelationshipCollection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class RelationshipContainerContext implements IRelationshipContainerContext
{
    private DatabaseConnection dbc;

    public RelationshipContainerContext(){
        dbc = new DatabaseConnection();
    }

    public RelationshipCollection GetRelationships(int userId)
    {
        RelationshipCollection rc = new RelationshipCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call GetRelationships(?)}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, userId);
                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        Relationship relationship = new Relationship();
                        relationship.setFriendOneId(rs.getInt("user_one_id"));
                        relationship.setFriendTwoId(rs.getInt("user_two_id"));
                        relationship.setFriendStatus(FriendStatus.valueOf(rs.getString("status")));
                        rc.getRelationships().add(relationship);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return rc;
    }
}
