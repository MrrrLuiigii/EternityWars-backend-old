package com.eternitywars.Logic.Lobby;

import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.Enums.LobbyPlayerStatus;
import com.eternitywars.Models.Lobby;
import com.eternitywars.Models.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LobbyLogicTest {
    private Player player1;
    private Player player2;
    private Lobby lobby;
    LobbyLogic lobbyLogic;


    private void Setup()
    {
        player1 = new Player(1, "wiebe", AccountStatus.Online);
        player2 = new Player(2, "Sjaak", AccountStatus.Offline);
        lobby = new Lobby();
        lobby.setPlayerOne(player1);
        lobby.setPlayerTwo(player2);
        lobbyLogic = new LobbyLogic();
    }

    @Test
    void playerNotReady()
    {
        Setup();
        lobbyLogic.PlayerNotReady(lobby, player1);
        assertEquals(LobbyPlayerStatus.NotReady, player1.getLobbyPlayerStatus());

        lobbyLogic.PlayerNotReady(lobby, player2);
        assertEquals(LobbyPlayerStatus.NotReady, player2.getLobbyPlayerStatus());
    }
}