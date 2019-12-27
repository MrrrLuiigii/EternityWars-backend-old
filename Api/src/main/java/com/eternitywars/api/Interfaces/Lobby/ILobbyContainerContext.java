package com.eternitywars.api.Interfaces.Lobby;

import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;

public interface ILobbyContainerContext
{
    Lobby AddLobby(Lobby lobby);

    boolean DeleteLobby(Lobby lobby);

    Lobby GetLobbyById(int lobbyId);

    LobbyCollection GetLobbies();
}
