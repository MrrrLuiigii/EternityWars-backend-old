package com.eternitywars.Logic.Collection;

import com.eternitywars.Models.Account;
import com.eternitywars.Models.Card;
import com.eternitywars.Models.CardCollection;
import com.eternitywars.Models.Deck;
import com.eternitywars.Models.DeckCollection;

public class CollectionLogic
{
    public CardCollection GetAllCardsByAccount(Account account)
    {
        //todo call api to get all cards by account
        return null;
    }

    public void AddCardToCardCollection(Account account, Card card)
    {
        //todo add card to collection by account
    }

    public DeckCollection GetAllDecksByAccount(Account account)
    {
        //todo call api to gt all decks by account
        return null;
    }

    public void AddDeckToDeckCollection(Account account, Deck deck)
    {
        //todo add deck to collection by account
    }

    public void RemoveDeckFromDeckCollection(Account account, Deck deck)
    {
        //todo remove deck from collection by account
    }
}
