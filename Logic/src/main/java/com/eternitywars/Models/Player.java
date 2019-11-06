package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.Enums.LobbyPlayerStatus;

import java.net.Socket;
import java.util.List;

public class Player extends Account
{
    private int hp;
    private int blueMana;
    private int deathEssence;
    private Deck deck;
    private List<Card> cardsInHand;
    private List<Card> cardsInDeck;
    private LobbyPlayerStatus lobbyPlayerStatus;

    public Player(int id, String username, AccountStatus accountStatus, Socket socket, int hp, int blueMana, int deathEssence, Deck deck, List<Card> cardsInHand, List<Card> cardsInDeck, LobbyPlayerStatus lobbyPlayerStatus)
    {
        super(id, username, accountStatus, socket);
        this.hp = hp;
        this.blueMana = blueMana;
        this.deathEssence = deathEssence;
        this.deck = deck;
        this.cardsInHand = cardsInHand;
        this.cardsInDeck = cardsInDeck;
        this.lobbyPlayerStatus = lobbyPlayerStatus;
    }

    public int getHp()
    {
        return hp;
    }

    public void setHp(int hp)
    {
        this.hp = hp;
    }

    public int getBlueMana()
    {
        return blueMana;
    }

    public void setBlueMana(int blueMana)
    {
        this.blueMana = blueMana;
    }

    public int getDeathEssence()
    {
        return deathEssence;
    }

    public void setDeathEssence(int deathEssence)
    {
        this.deathEssence = deathEssence;
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
