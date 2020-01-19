package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class Pack
{
    List<Card> Cards;

    public Pack()
    {
        Cards = new ArrayList<>();
    }

    public List<com.eternitywars.Models.Card> getCards() {
        return Cards;
    }

    public void setCards(List<com.eternitywars.Models.Card> cards) {
        Cards = cards;
    }
}
