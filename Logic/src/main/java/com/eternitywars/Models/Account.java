package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;
import org.eclipse.jetty.websocket.api.Session;

import java.net.Socket;

public abstract class Account
{
     int id;
     String username;
     AccountStatus accountStatus;
    Session session;

    public Account(){}

    public Account(int id, String username, AccountStatus accountStatus)
    {
        this.id = id;
        this.username = username;
        this.accountStatus = accountStatus;
    }

    public Account(int id,String username, AccountStatus accountStatus, Session session)
    {
        this.id = id;
        this.username = username;
        this.accountStatus = accountStatus;
        this.session = session;
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

    public Session getSession()
    {
        return session;
    }

    public void setSession(Session session)
    {
        this.session = session;
    }
}
