package com.eternitywars.Logic.Shop;

import com.eternitywars.Models.*;
import com.github.javafaker.Bool;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

public class CardPickerLogic
{
    private RestTemplate restTemplate = new RestTemplate();
    Random random = new Random();

    public Pack PickCards(User user, String  token)
    {

        CardCollection cardCollection = GetAllCards(token);
        System.out.println(cardCollection.getCards().size());
        Pack pack = new Pack();
        for(int i = 0; i < 3; i++)
        {
            int card_number = random.nextInt(cardCollection.getCards().size() + 1);
            Card card = cardCollection.getCards().get(card_number);
            AddCardToAccount(card , user, token);
            pack.getCards().add(card);
        }

        return pack;
    }

    public CardCollection GetAllCards(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<CardCollection> cardCollection = restTemplate.exchange("http://localhost:8083/api/private/card/get", HttpMethod.GET , request,  CardCollection.class);
        return cardCollection.getBody();
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
        JSONObject json = new JSONObject(cardAdder);
        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);

        restTemplate.postForObject("http://localhost:8083/api/private/card/add", request, Boolean.class);
        return true;
    }
}
