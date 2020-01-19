package com.eternitywars.api.Interfaces.Deck;

import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;

public interface IDeckContainerContext
{
    Deck AddDeck(Deck deck);

    boolean DeleteDeck(Deck deck);

    DeckCollection GetEmptyDecksByUserId(int userId);

    Deck GetEmptyDeckById(int deckId);

    DeckCollection GetDecksByUserId(int userId);

    Deck GetDeckById(int deckId);
}
