package com.eternitywars.api.Interfaces.Lobby;

import com.eternitywars.api.Models.LobbyData;
import com.eternitywars.api.Models.LobbyDataCollection;

public interface ILobbyContainerContext
{
    LobbyData GetLobbyById(int lobbyId);
    LobbyDataCollection GetLobbies();
}
