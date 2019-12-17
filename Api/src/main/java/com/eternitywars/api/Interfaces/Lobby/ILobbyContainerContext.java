package com.eternitywars.api.Interfaces.Lobby;

import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;

public interface ILobbyContainerContext
{
    Lobby GetLobbyById(int lobbyId);

    LobbyCollection GetLobbies();
}
