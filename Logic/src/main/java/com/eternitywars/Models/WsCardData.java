package com.eternitywars.Models;

import java.util.List;

public class WsCardData {
    private List<CardSlot> cardSlots;
    private int index;

    public WsCardData() {
    }

    public List<CardSlot> getCardSlots() {
        return cardSlots;
    }

    public void setCardSlots(List<CardSlot> cardSlot) {
        this.cardSlots = cardSlot;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
