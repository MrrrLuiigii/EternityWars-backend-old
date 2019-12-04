package com.eternitywars.api.Interfaces;

import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;

public interface IDeckContainerContext
{
    DeckCollection GetAllDeckById(int userid);
    boolean AddDeck(Deck deck);
    boolean SaveDeck(Deck deck);
    boolean DeleteDeck(Deck deck);
}
