package com.eternitywars.api.Interfaces.Deck;

import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;

public interface IDeckContainerContext {
    boolean AddDeck(Deck deck);

    boolean DeleteDeck(Deck deck);

    DeckCollection GetAllDecksByUserId(Deck deck);

    Deck GetDeckById(Deck deck);
}
