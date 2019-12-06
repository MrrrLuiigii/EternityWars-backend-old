package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;

import java.net.Socket;

public class User extends Account
{
    private String email;
    private int gold;
    private int packAmount;
    private FriendCollection friendCollection;
    private DeckCollection deckCollection;
    private CardCollection cardCollection;

    public User(){}

    public User(String username, String email)
    {
        this.username = username;
        this.email = email;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public int getGold()
    {
        return gold;
    }

    public void setGold(int gold)
    {
        this.gold = gold;
    }

    public int getPackAmount()
    {
        return packAmount;
    }

    public void setPackAmount(int packAmount)
    {
        this.packAmount = packAmount;
    }

    public FriendCollection getFriendCollection()
    {
        return friendCollection;
    }

    public void setFriendCollection(FriendCollection friendCollection)
    {
        this.friendCollection = friendCollection;
    }

    public DeckCollection getDeckCollection()
    {
        return deckCollection;
    }

    public void setDeckCollection(DeckCollection deckCollection)
    {
        this.deckCollection = deckCollection;
    }

    public CardCollection getCardCollection()
    {
        return cardCollection;
    }

    public void setCardCollection(CardCollection cardCollection)
    {
        this.cardCollection = cardCollection;
    }
}
