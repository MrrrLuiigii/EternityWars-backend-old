package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.Enums.LobbyPlayerStatus;
import org.eclipse.jetty.websocket.api.Session;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Player extends Account
{
    private Deck deck;
    private BoardRow boardRow;
    private List<Card> cardsInHand;
    private List<Card> cardsInDeck;
    private LobbyPlayerStatus lobbyPlayerStatus;
    private Hero hero;

    public Player() {
        this.cardsInHand = new ArrayList<>();
        this.cardsInDeck= new ArrayList<>();
        this.boardRow = new BoardRow();
    }



    public Player(int id, String username, AccountStatus accountStatus, Session session, Deck deck, List<Card> cardsInHand, List<Card> cardsInDeck, LobbyPlayerStatus lobbyPlayerStatus, BoardRow boardRow)
    {
        super(id, username, accountStatus, session);
        this.deck = deck;
        this.cardsInHand = cardsInHand;
        this.cardsInDeck = cardsInDeck;
        this.lobbyPlayerStatus = lobbyPlayerStatus;
        this.boardRow = boardRow;
    }

    public Player(int userId, String username, AccountStatus accountStatus) {
        super(userId, username, accountStatus);
    }

    public BoardRow getBoardRows() {
        return boardRow;
    }

    public void setBoardRows(BoardRow boardRows) {
        this.boardRow = boardRows;
    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
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

    public BoardRow getBoardRow() {
        return boardRow;
    }

    public void setBoardRow(BoardRow boardRow) {
        this.boardRow = boardRow;
    }
}
