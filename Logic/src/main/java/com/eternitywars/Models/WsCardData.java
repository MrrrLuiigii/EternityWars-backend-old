package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class WsCardData {
    private List<CardSlot> cardslots;
    private int index;

    public WsCardData() {
        this.cardslots = new ArrayList<>();
    }

    public List<CardSlot> getCardSlots() {
        return cardslots;
    }

    public void setCardSlots(List<CardSlot> cardSlot) {
        this.cardslots = cardSlot;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
