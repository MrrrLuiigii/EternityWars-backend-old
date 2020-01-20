package com.eternitywars.api.DAL.Repositories.Deck;

import com.eternitywars.api.DAL.Contexts.Deck.DeckSqlContext;
import com.eternitywars.api.Factories.Deck.DeckFactory;
import com.eternitywars.api.Factories.Friend.RelationshipContainerFactory;
import com.eternitywars.api.Interfaces.Deck.IDeckContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.Deck;

public class DeckRepository implements IDeckContext
{
    private DeckSqlContext deckSqlContext;

    public DeckRepository()
    {
        DeckFactory deckFactory = new DeckFactory();
        this.deckSqlContext = deckFactory.getDeckSqlContext();
    }

    public DeckRepository(DeckFactory deckFactory)
    {
        this.deckSqlContext = deckFactory.getTestDeckSqlContext();
    }

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
