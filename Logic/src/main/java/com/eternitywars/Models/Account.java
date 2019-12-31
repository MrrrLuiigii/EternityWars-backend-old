package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;
import org.eclipse.jetty.websocket.api.Session;

public abstract class Account
{
    protected String email;
    protected int userId;
    protected String username;
    protected AccountStatus accountStatus;
    protected Session session;

    public Account(String email, int userId, String username, AccountStatus accountStatus, Session session) {
        this.email = email;
        this.userId = userId;
        this.username = username;
        this.accountStatus = accountStatus;
        this.session = session;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Account(){}

    public Account(int userId, String username, AccountStatus accountStatus)
    {
        this.userId = userId;
        this.username = username;
        this.accountStatus = accountStatus;
    }

    public Account(int userId, String username, AccountStatus accountStatus, Session session)
    {
        this.userId = userId;
        this.username = username;
        this.accountStatus = accountStatus;
        this.session = session;
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

    public Session getSession()
    {
        return session;
    }

    public void setSession(Session session)
    {
        this.session = session;
    }
}
