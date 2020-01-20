package com.eternitywars.api.DAL.Contexts.Lobby;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.IDatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;
import com.eternitywars.api.Interfaces.Lobby.ILobbyContext;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.Player;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class LobbySqlContext implements ILobbyContext
{
    private IDatabaseConnection dbc;

    public LobbySqlContext(TestDatabaseConnection dbc)
    {
        this.dbc = dbc;
    }

    public LobbySqlContext(DatabaseConnection dbc) {
        this.dbc = dbc;
    }


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
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "update `player` " +
                    "set `lobby_player_status` = ? " +
                    "where `lobby_id` = ? and `user_id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setString(1, player.getLobbyPlayerStatus().toString());
                pst.setInt(2, lobby.getId());
                pst.setInt(3, player.getUserId());
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

    public Lobby UpdatePlayerDeck(Lobby lobby, Player player)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "update `player` " +
                    "set `deck_id` = ? " +
                    "where `lobby_id` = ? and `user_id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, player.getDeck().getDeckId());
                pst.setInt(2, lobby.getId());
                pst.setInt(3, player.getUserId());
                pst.executeUpdate();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return lobby;
    }
}
