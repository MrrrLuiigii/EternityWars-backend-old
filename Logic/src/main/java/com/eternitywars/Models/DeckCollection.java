package com.eternitywars.Models;

import java.util.List;

public class DeckCollection
{
    private List<Deck> decks;

    public DeckCollection(List<Deck> decks)
    {
        this.decks = decks;
    }

    public List<Deck> getDecks()
    {
        return decks;
    }

    public void setDecks(List<Deck> decks)
    {
        this.decks = decks;
    }
}
