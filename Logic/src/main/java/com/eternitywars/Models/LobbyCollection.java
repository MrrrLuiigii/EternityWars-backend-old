package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class LobbyCollection
{
    private List<Lobby> lobbies;

    public LobbyCollection()
    {
        lobbies = new ArrayList<>();
    }

    public List<Lobby> getLobbies()
    {
        return lobbies;
    }

    public void addLobby(Lobby lobby)
    {
        this.lobbies.add(lobby);
    }
}
