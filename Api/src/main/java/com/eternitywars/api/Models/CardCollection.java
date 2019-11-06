package com.eternitywars.api.Models;

import java.util.List;

public class CardCollection
{
    private List<Card> cards;

    public CardCollection(List<Card> cards)
    {
        this.cards = cards;
    }

    public List<Card> getCards()
    {
        return cards;
    }

    public void setCards(List<Card> cards)
    {
        this.cards = cards;
    }
}
