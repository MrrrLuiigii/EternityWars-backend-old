package com.eternitywars.Logic.Collection;

import com.eternitywars.Models.Account;
import com.eternitywars.Models.Card;
import com.eternitywars.Models.CardCollection;
import com.eternitywars.Models.Deck;
import com.eternitywars.Models.DeckCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class CollectionLogic
{
    @Autowired
    RestTemplate restTemplate = new RestTemplate();
    public CardCollection GetAllCardsByAccount(Account account)
    {
        //todo call api to get all cards by account
        return restTemplate.getForObject("example", CardCollection.class);
    }

    public boolean AddCardToCardCollection(Account account, Card card)
    {
        //todo add card to collection by account POST
        return restTemplate.getForObject("example", boolean.class);
    }

    public DeckCollection GetAllDecksByAccount(Account account)
    {
        //todo call api to gt all decks by account
        return restTemplate.getForObject("example", DeckCollection.class);
    }

    public boolean AddDeckToDeckCollection(Account account, Deck deck)
    {
        //todo add deck to collection by account POST
        return restTemplate.getForObject("example", boolean.class);
    }

    public boolean RemoveDeckFromDeckCollection(Account account, Deck deck)
    {
        //todo remove deck from collection by account
        return restTemplate.getForObject("example", boolean.class);
    }
}
