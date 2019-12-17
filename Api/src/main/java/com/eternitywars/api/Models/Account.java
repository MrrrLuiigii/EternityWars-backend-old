package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.AccountStatus;
import java.net.Socket;

public abstract class Account
{
    protected int userId;
    protected String username;
    protected AccountStatus accountStatus;
    protected Socket socket;

    public Account(){}

    public Account(int userId, String username, AccountStatus accountStatus)
    {
        this.userId = userId;
        this.username = username;
        this.accountStatus = accountStatus;
    }

    public Account(int userId, String username, AccountStatus accountStatus, Socket socket)
    {
        this.userId = userId;
        this.username = username;
        this.accountStatus = accountStatus;
        this.socket = socket;
    }

    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
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
