package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.LobbyPlayerStatus;

import java.util.List;

public class Player extends Account
{
    private LobbyPlayerStatus lobbyPlayerStatus;
    private Deck deck;
    private List<Card> cardsInHand;
    private List<Card> cardsInDeck;

    public Player(){}

    public Player(int id, String username, LobbyPlayerStatus lobbyPlayerStatus, int deckId)
    {
        this.userId = id;
        this.username = username;
        this.lobbyPlayerStatus = lobbyPlayerStatus;
        this.deck = new Deck(deckId);
    }

    // Getters & Setters

    public LobbyPlayerStatus getLobbyPlayerStatus()
    {
        return lobbyPlayerStatus;
    }

    public void setLobbyPlayerStatus(LobbyPlayerStatus lobbyPlayerStatus)
    {
        this.lobbyPlayerStatus = lobbyPlayerStatus;
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
