package com.eternitywars.api.Models;

public class LobbyData
{
    int lobbyId;
    int UserId;
    int accountReady;

    public LobbyData() { }

    public LobbyData(int lobbyId, int UserId, int accountReady)
    {
        this.lobbyId = lobbyId;
        this.UserId = UserId;
        this.accountReady = accountReady;
    }

    public int getLobbyId()
    {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId)
    {
        this.lobbyId = lobbyId;
    }

    public int getUserId()
    {
        return UserId;
    }

    public void setUserId(int userId)
    {
        this.UserId = userId;
    }

    public int getAccountReady()
    {
        return accountReady;
    }

    public void setAccountReady(int accountReady)
    {
        this.accountReady = accountReady;
    }
}
