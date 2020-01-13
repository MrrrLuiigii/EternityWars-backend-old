package com.eternitywars.Models;

import java.util.List;

public class BoardRow {
    List<CardSlot> cardSlotList;

    public BoardRow() {

    }

    public List<CardSlot> getCardSlotList() {
        return cardSlotList;
    }

    public void setCardSlotList(List<CardSlot> cardSlotList) {
        this.cardSlotList = cardSlotList;
    }
}
