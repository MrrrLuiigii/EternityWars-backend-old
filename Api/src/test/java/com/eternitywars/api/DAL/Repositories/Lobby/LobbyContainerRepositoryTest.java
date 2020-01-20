package com.eternitywars.api.DAL.Repositories.Lobby;

import com.eternitywars.api.Factories.Lobby.LobbyContainerFactory;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.Player;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.security.SecurityProperties;

import static org.junit.jupiter.api.Assertions.*;

class LobbyContainerRepositoryTest {

    private LobbyContainerRepository lobbyContainerRepository = new LobbyContainerRepository(new LobbyContainerFactory());

    private Lobby SetupLobby(){
        Lobby lobby = new Lobby();
        lobby.setId(1);
        lobby.setName("TestLobby");
        lobby.setDescription("desc");
        lobby.setPlayerOne(new Player());
        lobby.getPlayerOne().setUserId(1);
        return lobby;
    }

    @Test
    void addLobby() {
        Lobby lobbyToAdd = SetupLobby();
        Lobby returnLobby =  lobbyContainerRepository.AddLobby(lobbyToAdd);

        assertEquals(lobbyToAdd.getId(), returnLobby.getId());
        assertEquals(lobbyToAdd.getName(), returnLobby.getName());

    }

    @Test
    void deleteLobby() {
        Lobby lobbyToDelete = SetupLobby();
        assertEquals(true, lobbyContainerRepository.DeleteLobby(lobbyToDelete));
    }

    @Test
    void getLobbyById() {
        Lobby lobbyToAdd = SetupLobby();
        Lobby addedLobby =  lobbyContainerRepository.AddLobby(lobbyToAdd);
        Lobby returnlobby = lobbyContainerRepository.GetLobbyById(1);

        assertEquals(addedLobby.getId(), returnlobby.getId());
        assertEquals(addedLobby.getName(), returnlobby.getName());
    }

    @Test
    void getLobbies() {
        Lobby lobbyToAdd = SetupLobby();
        Lobby returnLobby =  lobbyContainerRepository.AddLobby(lobbyToAdd);
        assertEquals(1, lobbyContainerRepository.GetLobbies().getLobbies().size());
    }
}