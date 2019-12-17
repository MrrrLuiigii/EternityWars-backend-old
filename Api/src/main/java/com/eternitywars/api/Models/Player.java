package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.AccountStatus;
import java.net.Socket;
import java.util.List;

public class Player extends Account
{
    private int lobbyId;
    private boolean playerReady;
    private Deck deck;
    private List<Card> cardsInHand;
    private List<Card> cardsInDeck;

    public Player(int id, String username, AccountStatus accountStatus, Socket socket, Deck deck, List<Card> cardsInHand, List<Card> cardsInDeck, boolean playerReady)
    {
        super(id, username, accountStatus, socket);
        this.deck = deck;
        this.cardsInHand = cardsInHand;
        this.cardsInDeck = cardsInDeck;
        this.playerReady = playerReady;
    }

    public Player(int id, String username, boolean playerReady, int deckId)
    {
        this.userId = id;
        this.username = username;
        this.playerReady = playerReady;
        this.deck = new Deck(deckId);
    }

    public int getLobbyId()
    {
        return lobbyId;
    }

    public void setLobbyId(int lobbyId)
    {
        this.lobbyId = lobbyId;
    }

    public boolean getPlayerReady()
    {
        return playerReady;
    }

    public void setPlayerReady(boolean playerReady)
    {
        this.playerReady = playerReady;
    }

    public Deck getDeck()
    {
        return deck;
    }

    public void setDeck(Deck deck)
    {
        this.deck = deck;
    }

    public List<Card> getCardsInHand()
    {
        return cardsInHand;
    }

    public void setCardsInHand(List<Card> cardsInHand)
    {
        this.cardsInHand = cardsInHand;
    }

    public List<Card> getCardsInDeck()
    {
        return cardsInDeck;
    }

    public void setCardsInDeck(List<Card> cardsInDeck)
    {
        this.cardsInDeck = cardsInDeck;
    }
}
