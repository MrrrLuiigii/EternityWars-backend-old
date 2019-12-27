package com.eternitywars.api.DAL.Contexts.Friend;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContext;
import com.eternitywars.api.Models.Enums.FriendStatus;
import com.eternitywars.api.Models.Relationship;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class RelationshipSqlContext implements IRelationshipContext
{
    @Autowired
    private DatabaseConnection dbc;

    public boolean UpdateRelationship(Relationship relationship)
    {
        if (relationship.getFriendStatus() == FriendStatus.Accepted)
        {
            return UpdateRelationshipToAccepted(relationship);
        }
        else if (relationship.getFriendStatus() == FriendStatus.Blocked)
        {
            return UpdateRelationshipToBlocked(relationship);
        }

        return false;
    }

    private boolean UpdateRelationshipToBlocked(Relationship relationship)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "update `friend` set `status` = ? " +
                    "where `user_one_id` = ? and `user_two_id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setString(1, relationship.getFriendStatus().toString());
                pst.setInt(2, relationship.getFriendOneId());
                pst.setInt(3, relationship.getFriendTwoId());
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

    private boolean UpdateRelationshipToAccepted(Relationship relationship)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "update `friend` set `status` = ? " +
                    "where `user_one_id` = ? and `user_two_id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setString(1, relationship.getFriendStatus().toString());
                pst.setInt(2, relationship.getFriendTwoId());
                pst.setInt(3, relationship.getFriendOneId());
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
}
