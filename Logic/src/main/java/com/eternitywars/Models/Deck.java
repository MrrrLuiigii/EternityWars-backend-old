package com.eternitywars.Models;

import java.util.List;

public class Deck
{
    int deckId;
    List<Card> cards;

    public Deck(List<Card> cards, int deckid)
    {
        this.deckId = deckid;
        this.cards = cards;
    }

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}
