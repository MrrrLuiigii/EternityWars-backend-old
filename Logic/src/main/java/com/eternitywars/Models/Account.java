package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class Account
{
    private int id;
    private String name;
    private String email;
    private String password;
    private int gold;
    private int packsAmount;
    private List<Deck> decks;
    private List<Account> friends;

    public Account(){}

    public Account(int id, String name, String email, String password, int gold, int packsAmount)
    {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.gold = gold;
        this.packsAmount = packsAmount;
        decks = new ArrayList<>();
        friends = new ArrayList<>();
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

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public List<Deck> getDecks()
    {
        return decks;
    }

    public void setDecks(List<Deck> decks)
    {
        this.decks = decks;
    }

    public List<Account> getFriends()
    {
        return friends;
    }

    public void setFriends(List<Account> friends)
    {
        this.friends = friends;
    }

    public int getPacksAmount()
    {
        return packsAmount;
    }

    public void setPacksAmount(int packsAmount)
    {
        this.packsAmount = packsAmount;
    }
}
