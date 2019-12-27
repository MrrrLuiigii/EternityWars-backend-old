package com.eternitywars.api.DAL.Contexts.Lobby;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Lobby.ILobbyContainerContext;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;
import com.eternitywars.api.Models.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LobbyContainerSqlContext implements ILobbyContainerContext
{
    private DatabaseConnection dbc;

    public LobbyContainerSqlContext()
    {
        dbc = new DatabaseConnection();
    }

    //todo get deck from the players

    public Lobby AddLobby(Lobby lobby) {
        return null;
    }

    public Lobby DeleteLobby(Lobby lobby) {
        return null;
    }

    public Lobby GetLobbyById(int lobbyId)
    {
        Lobby lobby = null;

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `lobby`.`id`, `lobby`.`name`, `lobby`.`description`, `lobby`.`has_password`, " +
                    "`lobby`.`password`, `player`.`user_id`, `user`.`username`, `player`.`user_ready`, `player`.`deck_id` " +
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
                        boolean player_ready = rs.getBoolean("user_ready");
                        int deck_id = rs.getInt("deck_id");
                        Player player = new Player(player_id, username, player_ready, deck_id);

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
                    "`lobby`.`password`, `player`.`user_id`, `user`.`username`, `player`.`user_ready`, `player`.`deck_id` " +
                    "from `lobby` " +
                    "inner join `player` " +
                    "on `player`.`lobby_id` = `lobby`.`id` " +
                    "inner join `user` " +
                    "on `player`.`user_id` = `user`.`id`;";

            try (Statement st = conn.createStatement())
            {
                try (ResultSet rs = st.executeQuery(query))
                {
                    Lobby lobby = null;
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
                        boolean player_ready = rs.getBoolean("user_ready");
                        int deck_id = rs.getInt("deck_id");
                        Player player = new Player(player_id, username, player_ready, deck_id);

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
