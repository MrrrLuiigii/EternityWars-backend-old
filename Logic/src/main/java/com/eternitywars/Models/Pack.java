package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class Pack
{
    List<Card> Card;

    public Pack()
    {
        Card = new ArrayList<>();
    }

    public List<com.eternitywars.Models.Card> getCard() {
        return Card;
    }

    public void setCard(List<com.eternitywars.Models.Card> card) {
        Card = card;
    }
}
