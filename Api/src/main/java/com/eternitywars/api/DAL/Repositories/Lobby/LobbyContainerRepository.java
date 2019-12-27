package com.eternitywars.api.DAL.Repositories.Lobby;

import com.eternitywars.api.DAL.Contexts.Lobby.LobbyContainerSqlContext;
import com.eternitywars.api.Interfaces.Lobby.ILobbyContainerContext;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;

public class LobbyContainerRepository implements ILobbyContainerContext
{
    private ILobbyContainerContext lobbyContainerContext;

    public LobbyContainerRepository()
    {
        this.lobbyContainerContext = new LobbyContainerSqlContext();
    }

    public Lobby AddLobby(Lobby lobby) {
        return null;
    }

    public Lobby DeleteLobby(Lobby lobby) {
        return null;
    }

    public Lobby GetLobbyById(int lobbyId)
    {
        return lobbyContainerContext.GetLobbyById(lobbyId);
    }

    public LobbyCollection GetLobbies()
    {
        return lobbyContainerContext.GetLobbies();
    }
}
