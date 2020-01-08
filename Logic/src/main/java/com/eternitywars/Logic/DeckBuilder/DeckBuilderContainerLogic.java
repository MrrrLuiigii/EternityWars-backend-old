package com.eternitywars.Logic.DeckBuilder;

import com.eternitywars.Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class DeckBuilderContainerLogic
{

    private RestTemplate restTemplate = new RestTemplate();

    public boolean AddDeck(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("user").toString(), headers);

        return restTemplate.postForObject("http://localhost:8083/api/private/deck/add", request, boolean.class);
    }

    public DeckCollection GetAllDecks(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

       // JSONObject content = jsonObject.getJSONObject("Content");
        User user = gson.fromJson(jsonObject.getJSONObject("Content").toString(), User.class);
        HttpEntity<String> request = new HttpEntity<>(jsonObject.getJSONObject("Content").toString(), headers);
        ResponseEntity<DeckCollection> response = restTemplate.exchange("http://localhost:8083/api/private/deck/getByUserId/{userId}", HttpMethod.GET, request , DeckCollection.class, user.getUserId() );
        return response.getBody();
    }

    public Deck GetDeckById(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        Player player = gson.fromJson(content.getJSONObject("player").toString(), Player.class);
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("player").toString(), headers);
        ResponseEntity<Deck> response = restTemplate.exchange("http://localhost:8083/api/private/deck/get/{deckId}", HttpMethod.GET, request , Deck.class, player.getDeck().getDeckId());
        return response.getBody();
    }

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
        ResponseEntity<CardCollection> response = restTemplate.exchange("http://localhost:8083/api/private/deck/getAllCards", HttpMethod.GET, request , CardCollection.class);
        return response.getBody();
    }

    public boolean DeleteDeck(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("player").toString(), headers);
        return restTemplate.postForObject("http://localhost:8083/api/private/deck/delete", request, boolean.class);
    }

    public boolean SaveDeck(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("player").toString(), headers);
        return restTemplate.postForObject("http://localhost:8083/api/private/deck/save", request, boolean.class);
    }
}
