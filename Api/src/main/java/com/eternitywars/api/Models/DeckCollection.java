package com.eternitywars.api.Models;

import java.util.List;

public class DeckCollection
{
    public List<Deck> decks;

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

    public void AddDeck(Deck deck){ decks.add(deck);}
}
