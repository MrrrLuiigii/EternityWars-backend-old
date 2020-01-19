package com.eternitywars.api.DAL.Repositories.Lobby;

import com.eternitywars.api.DAL.Contexts.Lobby.LobbySqlContext;
import com.eternitywars.api.Interfaces.Lobby.ILobbyContext;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.Player;

public class LobbyRepository implements ILobbyContext
{
    private LobbySqlContext lobbySqlContext = new LobbySqlContext();



    public boolean JoinLobby(Lobby lobby, Player player)
    {
        return lobbySqlContext.JoinLobby(lobby, player);
    }

    public boolean LeaveLobby(Lobby lobby, Player player)
    {
        return lobbySqlContext.LeaveLobby(lobby, player);
    }

    public boolean UpdatePlayerStatus(Lobby lobby, Player player)
    {
        return lobbySqlContext.UpdatePlayerStatus(lobby, player);
    }

    public Lobby UpdatePlayerDeck(Lobby lobby, Player player)
    {
        return lobbySqlContext.UpdatePlayerDeck(lobby, player);
    }
}
