package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;

import java.net.Socket;

public class User extends Account
{
    private String email;
    private int gold;
    private int packsAmount;
    private FriendCollection friendCollection;
    private DeckCollection deckCollection;
    private CardCollection cardCollection;

    public User(int id, String username, AccountStatus accountStatus, Socket socket, String email, int gold, int packsAmount, FriendCollection friendCollection, DeckCollection deckCollection, CardCollection cardCollection)
    {
        super(id, username, accountStatus, socket);
        this.email = email;
        this.gold = gold;
        this.packsAmount = packsAmount;
        this.friendCollection = friendCollection;
        this.deckCollection = deckCollection;
        this.cardCollection = cardCollection;
    }

    public User(String username)
    {
        this.setUsername(username);
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

    public int getPacksAmount()
    {
        return packsAmount;
    }

    public void setPacksAmount(int packsAmount)
    {
        this.packsAmount = packsAmount;
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
