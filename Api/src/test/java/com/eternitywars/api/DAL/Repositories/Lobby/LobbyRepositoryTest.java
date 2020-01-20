package com.eternitywars.api.DAL.Repositories.Lobby;

import com.eternitywars.api.Factories.Lobby.LobbyContainerFactory;
import com.eternitywars.api.Factories.Lobby.LobbyFactory;
import com.eternitywars.api.Models.Lobby;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LobbyRepositoryTest {

    private LobbyRepository lobbyRepository = new LobbyRepository(new LobbyFactory());

    private Lobby EmptyLobby(){
        Lobby lobby = new Lobby();
        lobby.setName("Lobby");
        lobby.setDescription("desc");
        return lobby;
    }

    @Test
    void joinLobby() {
        //lobbyRepository.JoinLobby();
    }

    @Test
    void leaveLobby() {
       // lobbyRepository.LeaveLobby();
    }

    @Test
    void updatePlayerStatus() {
       // lobbyRepository.UpdatePlayerStatus();
    }

    @Test
    void updatePlayerDeck() {
       // lobbyRepository.UpdatePlayerDeck();
    }
}