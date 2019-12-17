package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.Enums.LobbyPlayerStatus;
import java.net.Socket;
import java.util.List;

public class Player extends Account
{
    private Deck deck;
    private List<Card> cardsInHand;
    private List<Card> cardsInDeck;
    private LobbyPlayerStatus lobbyPlayerStatus;

    public Player(int id, String username, AccountStatus accountStatus, Socket socket, Deck deck, List<Card> cardsInHand, List<Card> cardsInDeck, LobbyPlayerStatus lobbyPlayerStatus)
    {
        super(id, username, accountStatus, socket);
        this.deck = deck;
        this.cardsInHand = cardsInHand;
        this.cardsInDeck = cardsInDeck;
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

    public LobbyPlayerStatus getLobbyPlayerStatus()
    {
        return lobbyPlayerStatus;
    }

    public void setLobbyPlayerStatus(LobbyPlayerStatus lobbyPlayerStatus)
    {
        this.lobbyPlayerStatus = lobbyPlayerStatus;
    }
}
