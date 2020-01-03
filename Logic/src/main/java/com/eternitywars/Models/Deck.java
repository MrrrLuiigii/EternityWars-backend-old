package com.eternitywars.Models;

import java.util.List;

public class Deck
{
    int deckId;
    List<Card> cards;

    public Deck() {
    }

    public Deck(List<Card> cards, int deckid)
    {
        this.deckId = deckid;
        this.cards = cards;
    }
}
