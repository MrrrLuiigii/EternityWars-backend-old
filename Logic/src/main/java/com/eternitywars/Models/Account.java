package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;
import java.net.Socket;

public abstract class Account
{
     int id;
     String username;
     AccountStatus accountStatus;
     Socket socket;

    public Account(){}

    public Account(int id, String username, AccountStatus accountStatus)
    {
        this.id = id;
        this.username = username;
        this.accountStatus = accountStatus;
    }

    public Account(int id,String username, AccountStatus accountStatus, Socket socket)
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
