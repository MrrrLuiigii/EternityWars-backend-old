package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class CardCollection
{
    private List<Card> cards;

    public CardCollection()
    {
        this.cards = new ArrayList<>();
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
