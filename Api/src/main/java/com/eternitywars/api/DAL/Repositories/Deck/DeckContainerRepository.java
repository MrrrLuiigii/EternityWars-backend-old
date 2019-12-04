package com.eternitywars.api.DAL.Repositories.Deck;

import com.eternitywars.api.Interfaces.Deck.IDeckContainerContext;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;
import com.eternitywars.api.Models.User;

public class DeckContainerRepository implements IDeckContainerContext
{
    public boolean AddDeck(Deck deck)
    {
        return false;
    }

    public boolean DeleteDeck(Deck deck)
    {
        return false;
    }

    public DeckCollection GetAllDecksByUser(User user)
    {
        return null;
    }

    public Deck GetDeckById(int deckId)
    {
        return null;
    }
}
