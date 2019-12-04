package com.eternitywars.api.Interfaces.Deck;

import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;
import com.eternitywars.api.Models.User;

public interface IDeckContainerContext {
    boolean AddDeck(Deck deck);

    boolean DeleteDeck(Deck deck);

    DeckCollection GetAllDecksByUser(User user);

    Deck GetDeckById(int deckId);
}
