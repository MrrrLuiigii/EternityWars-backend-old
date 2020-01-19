package com.eternitywars.api.Models;

import java.util.ArrayList;
import java.util.List;

public class DeckCollection
{
    public List<Deck> decks;

    public DeckCollection()
    {
        this.decks = new ArrayList<>();
    }

    public List<Deck> getDecks()
    {
        return decks;
    }

    public void AddDeck(Deck deck){ decks.add(deck);}
}
