package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;
import org.eclipse.jetty.websocket.api.Session;

public class User extends Account
{
    private int gold;
    private int packAmount;
    private FriendCollection friendCollection;
    private DeckCollection deckCollection;
    private CardCollection cardCollection;


    public int getUserId()
    {
        return userId;
    }

    public void setUserId(int userId)
    {
        this.userId = userId;
    }


    public User(String email, int gold, int packAmount, FriendCollection friendCollection, DeckCollection deckCollection, CardCollection cardCollection) {
        this.email = email;
        this.gold = gold;
        this.packAmount = packAmount;
        this.friendCollection = friendCollection;
        this.deckCollection = deckCollection;
        this.cardCollection = cardCollection;
    }

    public User(int id, String username, AccountStatus accountStatus, String email, int gold, int packAmount, FriendCollection friendCollection, DeckCollection deckCollection, CardCollection cardCollection) {
        super(id, username, accountStatus);
        this.email = email;
        this.gold = gold;
        this.packAmount = packAmount;
        this.friendCollection = friendCollection;
        this.deckCollection = deckCollection;
        this.cardCollection = cardCollection;
    }

    public User(int id, String username, AccountStatus accountStatus, Session session, String email, int gold, int packAmount, FriendCollection friendCollection, DeckCollection deckCollection, CardCollection cardCollection) {
        super(id, username, accountStatus, session);
        this.email = email;
        this.gold = gold;
        this.packAmount = packAmount;
        this.friendCollection = friendCollection;
        this.deckCollection = deckCollection;
        this.cardCollection = cardCollection;
    }

    public User() {
        super();
    }

    public User(String username, String email)
    {
        this.username = username;
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
