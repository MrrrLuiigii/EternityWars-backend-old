package com.eternitywars.Logic.DeckBuilder;

import com.eternitywars.Models.Deck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class DeckBuilderContainerLogic
{
    @Autowired
    private RestTemplate restTemplate;

    public boolean AddDeck(Deck deck){
        //todo add deck stuff
        return true;
    }

    public List<String> GetAllDeckNames(){
        //todo get all deck names
        return null;
    }

    public Deck GetDeckByName(int DeckId){
        //todo get deck by name stuff
        return null;
    }

    public boolean DeleteDeck(Deck deck){
        //todo delete deck stuff
        return true;
    }

    public boolean SaveDeck(Deck deck){
        //todo save deck
        return true;
    }
}
