package com.eternitywars.Logic.DeckBuilder;

import com.eternitywars.Models.Card;
import com.eternitywars.Models.Deck;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class DeckBuilderLogic
{
    @Autowired
    private RestTemplate restTemplate;

    public boolean AddCard(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("deck").toString(), headers);
        return restTemplate.postForObject("http://localhost:8083/api/private/deck/addCard", request,  boolean.class);
    }

    public boolean RemoveCard(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("deck").toString(), headers);
        return restTemplate.postForObject("http://localhost:8083/api/private/deck/removeCard", request,  boolean.class);
    }
}
