package com.eternitywars.api.Interfaces.Lobby;

import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.Player;

public interface ILobbyContext
{
    boolean JoinLobby(Lobby lobby, Player player);

    boolean LeaveLobby(Lobby lobby, Player player);

    boolean UpdatePlayerStatus(Lobby lobby, Player player);

    Lobby UpdatePlayerDeck(Lobby lobby, Player player);
}
