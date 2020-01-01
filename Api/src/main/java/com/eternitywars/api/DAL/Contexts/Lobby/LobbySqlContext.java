package com.eternitywars.api.DAL.Contexts.Lobby;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Lobby.ILobbyContext;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.Player;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LobbySqlContext implements ILobbyContext
{
    private DatabaseConnection dbc = new DatabaseConnection();



    public boolean JoinLobby(Lobby lobby, Player player)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call JoinLobby(?, ?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, lobby.getId());
                cst.setInt(2, player.getUserId());
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

    public boolean LeaveLobby(Lobby lobby, Player player)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "delete from `player` where `lobby_id` = ? and `user_id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, lobby.getId());
                pst.setInt(2, player.getUserId());
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

    public boolean UpdatePlayerStatus(Lobby lobby, Player player)
    {
        return true;
    }

    public boolean UpdatePlayerDeck(Lobby lobby, Player player)
    {
        return true;
    }
}
