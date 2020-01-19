package com.eternitywars.api.DAL.Repositories.Deck;

import com.eternitywars.api.DAL.Contexts.Deck.DeckSqlContext;
import com.eternitywars.api.Interfaces.Deck.IDeckContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.Deck;

public class DeckRepository implements IDeckContext
{
    private DeckSqlContext deckSqlContext = new DeckSqlContext();

    public boolean AddCard(Deck deck, Card card)
    {
        return deckSqlContext.AddCard(deck, card);
    }

    public boolean DeleteCard(Deck deck, Card card)
    {
        return deckSqlContext.DeleteCard(deck, card);
    }

    public boolean UpdateDeckName(Deck deck)
    {
        return deckSqlContext.UpdateDeckName(deck);
    }
}
