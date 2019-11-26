package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.LobbyContainerContext;
import com.eternitywars.api.Interfaces.ILobbyContainerContext;
import com.eternitywars.api.Models.LobbyDataCollection;
import com.eternitywars.api.Models.LobbyData;

public class LobbyContainerRepository implements ILobbyContainerContext
{
    private ILobbyContainerContext lobbyContainerContext;

    public LobbyContainerRepository()
    {
        this.lobbyContainerContext = new LobbyContainerContext();
    }

    public LobbyData GetLobbyById(int lobbyId)
    {
        return lobbyContainerContext.GetLobbyById(lobbyId);
    }

    public LobbyDataCollection GetLobbies()
    {
        return lobbyContainerContext.GetLobbies();
    }
}