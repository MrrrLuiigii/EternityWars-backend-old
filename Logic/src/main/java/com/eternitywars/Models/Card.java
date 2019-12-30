package com.eternitywars.Models;

public class Card
{
    int cardId;
    String cardName;

    public Card(int cardId, String cardName)
    {
        this.cardId = cardId;
        this.cardName = cardName;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }
}
