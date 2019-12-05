package com.eternitywars.api.Interfaces.Deck;

import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.Deck;

public interface IDeckContext {
    boolean AddCard(Deck deck, Card card);

    boolean DeleteCard(Deck deck, Card card);

    boolean UpdateDeck(Deck deck);

    CardCollection GetCardsInDeck(Deck deck);
}
