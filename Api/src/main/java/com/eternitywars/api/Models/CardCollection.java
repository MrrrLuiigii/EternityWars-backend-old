package com.eternitywars.api.Models;

import java.util.ArrayList;
import java.util.List;

public class CardCollection
{
    private List<Card> cards;

    public CardCollection()
    {
        cards = new ArrayList<>();
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
