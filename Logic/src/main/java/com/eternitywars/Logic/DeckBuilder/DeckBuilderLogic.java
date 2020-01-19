package com.eternitywars.Logic.DeckBuilder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class DeckBuilderLogic
{
    private RestTemplate restTemplate = new RestTemplate();

    public boolean AddCard(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.getJSONObject("Content").toString(), headers);
        return restTemplate.postForObject("http://localhost:8083/api/private/deck/addCard", request, boolean.class);
    }

    public boolean RemoveCard(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        HttpEntity<String> request = new HttpEntity<>(jsonObject.getJSONObject("Content").toString(), headers);

        return restTemplate.postForObject("http://localhost:8083/api/private/deck/deleteCard", request, boolean.class);
    }
}
