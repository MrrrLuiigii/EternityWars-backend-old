package com.eternitywars.Models;

import com.eternitywars.Models.Enums.LobbyStatus;

import java.util.ArrayList;
import java.util.List;

public class Lobby
{
    int lobbyId;
    LobbyStatus status;
    Player playerOne;
    Player PlayerTwo;

    public Lobby(int lobbyId)
    {
        this.lobbyId = lobbyId;
    }

    public Lobby() {
    }

    public int getLobbyId() {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId) {
        this.lobbyId = lobbyId;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPLayerTwo() {
        return PlayerTwo;
    }

    public void setPLayerTwo(Player PLayerTwo) {
        this.PlayerTwo = PLayerTwo;
    }

    public LobbyStatus getStatus() {
        return status;
    }

    public void setStatus(LobbyStatus status) {
        this.status = status;
    }
}
