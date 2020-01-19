package com.eternitywars.Models;

import com.eternitywars.Models.Enums.LobbyStatus;

import java.util.ArrayList;
import java.util.List;

public class Lobby
{
    private int id;
    private String name;
    private String description;
    private boolean hasPassword;
    private String password;
    private Player playerOne;
    private Player playerTwo;

    public Lobby(){}

    public Lobby(int lobbyId, String name, String description, boolean hasPassword, String password)
    {
        this.id = lobbyId;
        this.name = name;
        this.description = description;
        this.hasPassword = hasPassword;
        this.password = password;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public boolean getHasPassword()
    {
        return hasPassword;
    }

    public void setHasPassword(boolean hasPassword)
    {
        this.hasPassword = hasPassword;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Player getPlayerOne()
    {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne)
    {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo()
    {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo)
    {
        this.playerTwo = playerTwo;
    }

    public void setStatus(LobbyStatus full) {
    }
}
