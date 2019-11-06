package com.eternitywars.api.Models;

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
}
