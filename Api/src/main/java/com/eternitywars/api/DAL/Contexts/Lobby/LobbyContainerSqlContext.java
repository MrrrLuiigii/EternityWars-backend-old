package com.eternitywars.api.DAL.Contexts.Lobby;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.IDatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;
import com.eternitywars.api.Interfaces.Lobby.ILobbyContainerContext;
import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.Enums.LobbyPlayerStatus;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;
import com.eternitywars.api.Models.Player;

import java.sql.*;

public class LobbyContainerSqlContext implements ILobbyContainerContext
{
    private IDatabaseConnection dbc;

    public LobbyContainerSqlContext(TestDatabaseConnection dbc)
    {
        this.dbc = dbc;
    }

    public LobbyContainerSqlContext(DatabaseConnection dbc) {
        this.dbc = dbc;
    }


    public Lobby AddLobby(Lobby lobby)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call AddLobby(?, ?, ?, ?, ?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setString(1, lobby.getName());
                cst.setString(2, lobby.getDescription());
                cst.setBoolean(3, lobby.getHasPassword());
                cst.setString(4, lobby.getPassword());
                cst.setInt(5, lobby.getPlayerOne().getUserId());

                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        lobby.setId(rs.getInt("id"));
                    }
                }

            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return lobby;
    }

    public boolean DeleteLobby(Lobby lobby)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call DeleteLobby(?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, lobby.getId());
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

    public Lobby GetLobbyById(int lobbyId)
    {
        Lobby lobby = new Lobby();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `lobby`.`id`, `lobby`.`name`, `lobby`.`description`, `lobby`.`has_password`, " +
                    "`lobby`.`password`, `player`.`user_id`, `user`.`username`, `player`.`lobby_player_status`, `player`.`deck_id` " +
                    "from `lobby` " +
                    "inner join `player` " +
                    "on `player`.`lobby_id` = `lobby`.`id` " +
                    "inner join `user` " +
                    "on `player`.`user_id` = `user`.`id` " +
                    "where `lobby`.`id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, lobbyId);

                try (ResultSet rs = pst.executeQuery())
                {
                    int oldLobbyId = 0;

                    while (rs.next())
                    {
                        int rsLobbyId = rs.getInt("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        boolean hasPassword = rs.getBoolean("has_password");
                        String password = rs.getString("password");

                        int player_id = rs.getInt("user_id");
                        String username = rs.getString("username");
                        LobbyPlayerStatus lobbyPlayerStatus = LobbyPlayerStatus.valueOf(rs.getString("lobby_player_status"));
                        int deck_id = rs.getInt("deck_id");
                        Player player = new Player(player_id, username, lobbyPlayerStatus, deck_id);

                        if (oldLobbyId != rsLobbyId)
                        {
                            lobby = new Lobby(rsLobbyId, name, description, hasPassword, password);
                            lobby.setPlayerOne(player);
                        }
                        else
                        {
                            lobby.setPlayerTwo(player);
                        }

                        oldLobbyId = rsLobbyId;
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return lobby;
    }

    public LobbyCollection GetLobbies()
    {
        LobbyCollection lobbyCollection = new LobbyCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `lobby`.`id`, `lobby`.`name`, `lobby`.`description`, `lobby`.`has_password`, " +
                    "`lobby`.`password`, `player`.`user_id`, `user`.`username`, `player`.`lobby_player_status`, `player`.`deck_id` " +
                    "from `lobby` " +
                    "inner join `player` " +
                    "on `player`.`lobby_id` = `lobby`.`id` " +
                    "inner join `user` " +
                    "on `player`.`user_id` = `user`.`id`;";

            try (Statement st = conn.createStatement())
            {
                try (ResultSet rs = st.executeQuery(query))
                {
                    Lobby lobby = new Lobby();
                    int oldLobbyId = 0;

                    while (rs.next())
                    {
                        int lobbyId = rs.getInt("id");
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        boolean hasPassword = rs.getBoolean("has_password");
                        String password = rs.getString("password");

                        int player_id = rs.getInt("user_id");
                        String username = rs.getString("username");
                        LobbyPlayerStatus lobbyPlayerStatus = LobbyPlayerStatus.valueOf(rs.getString("lobby_player_status"));
                        int deck_id = rs.getInt("deck_id");
                        Player player = new Player(player_id, username, lobbyPlayerStatus, deck_id);

                        if (oldLobbyId != lobbyId)
                        {
                            lobby = new Lobby(lobbyId, name, description, hasPassword, password);
                            lobbyCollection.addLobby(lobby);
                            lobby.setPlayerOne(player);
                        }
                        else
                        {
                            lobby.setPlayerTwo(player);
                        }

                        oldLobbyId = lobbyId;
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return lobbyCollection;
    }
}
