package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.AccountStatus;

import java.net.Socket;

public abstract class Account
{
    private int id;
    private String googleId;
    private String username;
    private AccountStatus accountStatus;
    private Socket socket;

    public Account(){}

    public Account(int id, String username, AccountStatus accountStatus, Socket socket)
    {
        this.id = id;
        this.username = username;
        this.accountStatus = accountStatus;
        this.socket = socket;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getGoogleId()
    {
        return googleId;
    }

    public void setGoogleId(String googleId)
    {
        this.googleId = googleId;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public AccountStatus getAccountStatus()
    {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus)
    {
        this.accountStatus = accountStatus;
    }

    public Socket getSocket()
    {
        return socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }
}
