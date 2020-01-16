package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class BoardRow {
    List<CardSlot> cardSlotList;

    public BoardRow() {
        cardSlotList = new ArrayList<>();
        AddSlots(cardSlotList);
    }

    private void AddSlots(List<CardSlot> cardSlotList){
        for (int i = 0; i < 6; i++) {
            cardSlotList.add(new CardSlot());
        }
    }

    public List<CardSlot> getCardSlotList() {
        return cardSlotList;
    }

    public void setCardSlotList(List<CardSlot> cardSlotList) {
        this.cardSlotList = cardSlotList;
    }
}
