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

    public DeckCollection GetAllDecksByUserId(int userId)
    {
        return deckContainerSqlContext.GetAllDecksByUserId(userId);
    }

    public Deck GetDeckById(int deckId)
    {
        return deckContainerSqlContext.GetDeckById(deckId);
    }
}
