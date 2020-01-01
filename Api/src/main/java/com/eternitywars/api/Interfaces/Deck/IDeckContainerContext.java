package com.eternitywars.api.Interfaces.Deck;

import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;

public interface IDeckContainerContext
{
    Deck AddDeck(Deck deck);

    boolean DeleteDeck(Deck deck);

    DeckCollection GetAllDecksByUserId(int userId);

    Deck GetDeckById(int deckId);
}
