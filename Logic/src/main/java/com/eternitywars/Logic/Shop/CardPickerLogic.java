package com.eternitywars.Logic.Shop;

import com.eternitywars.Models.Card;
import com.eternitywars.Models.CardCollection;
import com.eternitywars.Models.Pack;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class CardPickerLogic
{
    private RestTemplate restTemplate = new RestTemplate();

    public Pack PickCards()
    {
        Random random = new Random();
        CardCollection cardCollection = GetAllCards();
        Pack pack = new Pack();
        for(int i = 0; i < 2; i++)
        {
            int card_number = random.nextInt(cardCollection.getCards().size());
            pack.getCard().add(GetCardById(card_number));
        }
        return pack;
    }

    public CardCollection GetAllCards()
    {
        return  restTemplate.getForObject("http://localhost:8083/api/public/card/get", CardCollection.class);
    }

    public Card GetCardById(int id)
    {
        return restTemplate.getForObject("http://localhost:8083/api/private/get/" + id, Card.class);
    }
}
