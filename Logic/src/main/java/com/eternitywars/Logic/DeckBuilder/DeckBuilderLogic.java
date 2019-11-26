package com.eternitywars.Logic.DeckBuilder;

import com.eternitywars.Models.Card;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class DeckBuilderLogic
{
    @Autowired
    private RestTemplate restTemplate;

    public boolean AddCard(Card card){
        //todo add card stuff POST
        return restTemplate.getForObject("example", boolean.class);
    }

    public boolean RemoveCard(Card card){
        //todo remove card stuff POST
        return restTemplate.getForObject("example", boolean.class);
    }

}
