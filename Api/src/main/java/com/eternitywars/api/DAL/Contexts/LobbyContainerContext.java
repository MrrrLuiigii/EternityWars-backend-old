package com.eternitywars.api.DAL.Contexts;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.ILobbyContainerContext;
import com.eternitywars.api.Models.LobbyData;
import com.eternitywars.api.Models.LobbyDataCollection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LobbyContainerContext implements ILobbyContainerContext
{
    private DatabaseConnection dbc;

    public LobbyContainerContext()
    {
        dbc = new DatabaseConnection();
    }

    public LobbyData GetLobbyById(int lobbyId)
    {
        return null;
    }

    public LobbyDataCollection GetLobbies()
    {
        LobbyDataCollection lobbyDataCollection = new LobbyDataCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call GetLobbyData()}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        LobbyData lobbyData = new LobbyData();
                        lobbyData.setLobbyId(rs.getInt("id"));
                        lobbyData.setUserId(rs.getInt("user_id"));
//                        lobbyData.setAccountReady(rs.getInt("user_ready"));
                        lobbyDataCollection.getLobbies().add(lobbyData);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }
        return lobbyDataCollection;
    }
}
