package com.eternitywars.Logic.DeckBuilder;

import com.eternitywars.Models.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class DeckBuilderContainerLogic
{
    @Autowired
    private RestTemplate restTemplate;

    public boolean AddDeck(Deck deck){
        //todo add deck stuff POST
        return restTemplate.getForObject("example", boolean.class);
    }

    public ArrayList GetAllDeckNames(){
        //todo get all deck names
        return restTemplate.getForObject("example", ArrayList.class);
    }

    public Deck GetDeckById(int DeckId){
        //todo get deck by name stuff
        return restTemplate.getForObject("example", Deck.class);
    }

    public boolean DeleteDeck(Deck deck){
        //todo delete deck stuff
        return restTemplate.getForObject("example", boolean.class);
    }

    public boolean SaveDeck(Deck deck){
        //todo save deck POST
        return restTemplate.getForObject("example", boolean.class);
    }
}
