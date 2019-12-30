package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.AccountStatus;

import javax.websocket.Session;
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

    public User(int userId)
    {
        this.userId = userId;
    }

    public User(int id, String email, String username, AccountStatus accountStatus, int gold, int packAmount)
    {
        super(id, username, accountStatus);
        this.email = email;
        this.gold = gold;
        this.packAmount = packAmount;
    }

    public User(int id, String username, AccountStatus accountStatus, Session session, String email, int gold, int packAmount, FriendCollection friendCollection, DeckCollection deckCollection, CardCollection cardCollection)
    {
        super(id, username, accountStatus, session);
        this.email = email;
        this.gold = gold;
        this.packAmount = packAmount;
        this.friendCollection = friendCollection;
        this.deckCollection = deckCollection;
        this.cardCollection = cardCollection;
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
