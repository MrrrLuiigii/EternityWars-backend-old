package com.eternitywars.api.DAL.Contexts.Friend;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContext;
import com.eternitywars.api.Models.Enums.FriendStatus;
import com.eternitywars.api.Models.Relationship;
import java.sql.CallableStatement;
import java.sql.Connection;

public class RelationshipSqlContext implements IRelationshipContext
{
    private DatabaseConnection dbc = new DatabaseConnection();

    public boolean UpdateRelationship(Relationship relationship)
    {
        if (relationship.getFriendStatus() == FriendStatus.Accepted)
        {
            return AcceptFriend(relationship);
        }
        else if (relationship.getFriendStatus() == FriendStatus.Blocked)
        {
            return BlockFriend(relationship);
        }

        return false;
    }

    private boolean BlockFriend(Relationship relationship)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call BlockFriend(?, ?, ?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, relationship.getFriendOneId());
                cst.setInt(2, relationship.getFriendTwoId());
                cst.setString(3, relationship.getFriendStatus().toString());
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

    private boolean AcceptFriend(Relationship relationship)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call AcceptFriend(?, ?, ?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, relationship.getFriendOneId());
                cst.setInt(2, relationship.getFriendTwoId());
                cst.setString(3, relationship.getFriendStatus().toString());
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
}
