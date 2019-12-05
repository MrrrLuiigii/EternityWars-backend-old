package com.eternitywars.api.Models;

import java.util.ArrayList;
import java.util.List;

public class LobbyDataCollection
{
    List<LobbyData> Lobbydata;

    public LobbyDataCollection()
    {
        this.Lobbydata = new ArrayList<>();
    }

    public List<LobbyData> getLobbies()
    {
        return Lobbydata;
    }

    public void setLobbies(List<LobbyData> lobbies)
    {
        this.Lobbydata = Lobbydata;
    }
}
