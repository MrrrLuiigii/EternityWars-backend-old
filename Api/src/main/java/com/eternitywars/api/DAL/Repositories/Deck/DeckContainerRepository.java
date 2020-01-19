package com.eternitywars.api.DAL.Repositories.Deck;

import com.eternitywars.api.DAL.Contexts.Deck.DeckContainerSqlContext;
import com.eternitywars.api.Interfaces.Deck.IDeckContainerContext;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;

public class DeckContainerRepository implements IDeckContainerContext
{
    private DeckContainerSqlContext deckContainerSqlContext = new DeckContainerSqlContext();

    public Deck AddDeck(Deck deck)
    {
        return deckContainerSqlContext.AddDeck(deck);
    }

    public boolean DeleteDeck(Deck deck)
    {
        return deckContainerSqlContext.DeleteDeck(deck);
    }

    public DeckCollection GetEmptyDecksByUserId(int userId)
    {
        return deckContainerSqlContext.GetEmptyDecksByUserId(userId);
    }

    public Deck GetEmptyDeckById(int deckId)
    {
        return deckContainerSqlContext.GetEmptyDeckById(deckId);
    }

    public DeckCollection GetDecksByUserId(int userId)
    {
        return deckContainerSqlContext.GetDecksByUserId(userId);
    }

    public Deck GetDeckById(int deckId)
    {
        return deckContainerSqlContext.GetDeckById(deckId);
    }
}
