package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.DeckContainerContext;
import com.eternitywars.api.Interfaces.Deck.IDeckContainerContext;
import com.eternitywars.api.Models.DeckCollection;

public class DeckContainerRepository
{
    private IDeckContainerContext deckContainer;

    public DeckContainerRepository()
    {
        this.deckContainer = new DeckContainerContext();
    }

    public DeckCollection GetAllDeckById(int userId)
    {
        return deckContainer.GetAllDeckById(userId);
    }
}
