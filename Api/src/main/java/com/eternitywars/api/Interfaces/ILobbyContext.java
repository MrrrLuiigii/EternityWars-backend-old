package com.eternitywars.api.Interfaces;

import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;

public interface ILobbyContext
{
    Lobby GetLobbyById(int lobbyId);
    LobbyCollection GetLobbies();
}
