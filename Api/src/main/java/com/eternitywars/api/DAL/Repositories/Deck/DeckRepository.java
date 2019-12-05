package com.eternitywars.api.DAL.Repositories.Deck;

import com.eternitywars.api.DAL.Contexts.Deck.DeckContext;
import com.eternitywars.api.Interfaces.Deck.IDeckContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.Deck;

public class DeckRepository implements IDeckContext
{
    private IDeckContext deckContext;

    public DeckRepository()
    {
        this.deckContext = new DeckContext();
    }

    public boolean AddCard(Deck deck, Card card)
    {
        return false;
    }

    public boolean DeleteCard(Deck deck, Card card)
    {
        return false;
    }

    public boolean UpdateDeck(Deck deck)
    {
        return false;
    }

    @Override
    public CardCollection GetCardsInDeck(Deck deck)
    {
        return deckContext.GetCardsInDeck(deck);
    }
}
