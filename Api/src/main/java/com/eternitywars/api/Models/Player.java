package com.eternitywars.api.Models;

import java.util.List;

public class Player extends Account
{
    private boolean playerReady;
    private Deck deck;
    private List<Card> cardsInHand;
    private List<Card> cardsInDeck;

    public Player(int id, String username, boolean playerReady, int deckId)
    {
        this.userId = id;
        this.username = username;
        this.playerReady = playerReady;
        this.deck = new Deck(deckId);
    }

    // Getters & Setters

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
