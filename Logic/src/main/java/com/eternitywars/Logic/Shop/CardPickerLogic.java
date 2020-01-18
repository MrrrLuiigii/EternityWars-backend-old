package com.eternitywars.Logic.Shop;

import com.eternitywars.Models.*;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CardPickerLogic
{
    private RestTemplate restTemplate = new RestTemplate();

    public Pack PickCards(User user, String  token)
    {
        Random random = new Random();
        CardCollection cardCollection = GetAllCards();
        System.out.println(cardCollection.getCards().size());
        Pack pack = new Pack();
        for(int i = 0; i < 3; i++)
        {
            int card_number = random.nextInt(cardCollection.getCards().size() + 1);
            System.out.println(card_number);
            Card card = cardCollection.getCards().get(card_number);
            AddCardToAccount(card , user, token);
            pack.getCard().add(card);
        }

        return pack;
    }

    public CardCollection GetAllCards()
    {
        CardCollection cardCollection = restTemplate.getForObject("http://localhost:8083/api/public/card/get", CardCollection.class);
        System.out.println(cardCollection);
        return cardCollection;
    }

    public Card GetCardById(int id)
    {
        return restTemplate.getForObject("http://localhost:8083/api/public/card/get/" + id, Card.class);
    }

    public boolean AddCardToAccount(Card card, User user, String token)
    {
        CardAdder cardAdder = new CardAdder();
        cardAdder.setUserid(user.getUserId());
        cardAdder.setCardid(card.getCardId());
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject json = new JSONObject(card);
        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
        restTemplate.postForObject("http://localhost:8083/api/private/card/add", request, Card.class);
        return true;
    }
}
