package com.eternitywars.Logic.Collection;

import com.eternitywars.Models.Account;
import com.eternitywars.Models.Card;
import com.eternitywars.Models.CardCollection;
import com.eternitywars.Models.Deck;
import com.eternitywars.Models.DeckCollection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CollectionLogic
{
    @Autowired
    RestTemplate restTemplate = new RestTemplate();
    public CardCollection GetAllCardsByAccount(JSONObject jsonObject)
    {
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("account").toString(), headers);
        ResponseEntity<CardCollection> response = restTemplate.exchange("http://localhost:8083/api/private/collection/getAllCards", HttpMethod.GET, request , CardCollection.class);
        return response.getBody();
    }

    public boolean AddCardToCardCollection(Account account, Card card)
    {
        //todo add card to collection by account POST
        return restTemplate.getForObject("example", boolean.class);
    }
}
