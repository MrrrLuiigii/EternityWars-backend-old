package com.eternitywars.Models;

import java.util.List;

public class Game
{
    List<Player> connectedPlayers;
    int playerTurn;
    int id;
    int timer;
    boolean victory; //true player one, false player two???

    public Game()
    {

    }

    public List<Player> getConnectedPlayers() {
        return connectedPlayers;
    }

    public void setConnectedPlayers(List<Player> connectedPlayers) {
        this.connectedPlayers = connectedPlayers;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayerTurn() {
        return playerTurn;
    }

    public void setPlayerTurn(int playerTurn) {
        this.playerTurn = playerTurn;
    }

}
