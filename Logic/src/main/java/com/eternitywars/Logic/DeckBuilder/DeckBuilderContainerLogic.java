package com.eternitywars.Logic.DeckBuilder;

import com.eternitywars.Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

public class DeckBuilderContainerLogic
{
    private RestTemplate restTemplate = new RestTemplate();

    private HttpHeaders GetHttpHeaders(JSONObject jsonObject)
    {
        String token = jsonObject.getString("Token");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }

    private Object SendPost(String url, HttpHeaders httpHeaders, Object object)
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        HttpEntity<String> request = new HttpEntity<>(gson.toJson(object), httpHeaders);

        //todo breaking with deletedeck on sending request -> not using this method right now
        return restTemplate.postForObject(url, request, object.getClass());
    }

    private Object SendGet(String url, HttpHeaders httpHeaders, Object object, int parameter)
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        //todo getting all 0 and null back -> not using this method right now
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(object), httpHeaders);
        ResponseEntity<?> response = restTemplate.exchange(url, HttpMethod.GET, request, object.getClass(), parameter);
        return response.getBody();
    }

    public Deck AddDeck(JSONObject jsonObject)
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        int userId = content.getInt("userId");
        Deck deck = gson.fromJson(content.getJSONObject("deck").toString(), Deck.class);
        deck.setUserId(userId);

        HttpHeaders httpHeaders = GetHttpHeaders(jsonObject);
        String url = "http://localhost:8083/api/private/deck/add";
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(deck), httpHeaders);
        return restTemplate.postForObject(url, request, Deck.class);
    }

    public boolean DeleteDeck(JSONObject jsonObject)
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        Deck deck = gson.fromJson(content.toString(), Deck.class);

        HttpHeaders httpHeaders = GetHttpHeaders(jsonObject);
        String url = "http://localhost:8083/api/private/deck/delete";
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(deck), httpHeaders);
        return restTemplate.postForObject(url, request, boolean.class);
    }

    public DeckCollection GetAllDecks(JSONObject jsonObject)
    {
        HttpHeaders httpHeaders =  GetHttpHeaders(jsonObject);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        User user = gson.fromJson(jsonObject.getJSONObject("Content").toString(), User.class);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(user), httpHeaders);

        String url = "http://localhost:8083/api/private/deck/getByUserId/{userId}";
        ResponseEntity<DeckCollection> response = restTemplate.exchange(url, HttpMethod.GET, request, DeckCollection.class, user.getUserId());
        return response.getBody();
    }

    public DeckCollection GetAllEmptyDecks(JSONObject jsonObject)
    {
        HttpHeaders httpHeaders = GetHttpHeaders(jsonObject);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        User user = gson.fromJson(jsonObject.getJSONObject("Content").toString(), User.class);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(user), httpHeaders);

        String url = "http://localhost:8083/api/private/deck/getEmptyByUserId/{userId}";
        ResponseEntity<DeckCollection> response = restTemplate.exchange(url, HttpMethod.GET, request, DeckCollection.class, user.getUserId());
        return response.getBody();
    }

    public Deck GetDeckById(JSONObject jsonObject)
    {
        HttpHeaders httpHeaders = GetHttpHeaders(jsonObject);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        Player player = gson.fromJson(content.getJSONObject("player").toString(), Player.class);
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("player").toString(), httpHeaders);

        String url = "http://localhost:8083/api/private/deck/get/{deckId}";
        ResponseEntity<Deck> response = restTemplate.exchange(url, HttpMethod.GET, request , Deck.class, player.getDeck().getDeckId());
        return response.getBody();
    }

    public Deck GetEmptyDeckById(int id, String token)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        String url = "http://localhost:8083/api/private/deck/getEmptyByDeckId/{deckId}";
        ResponseEntity<Deck> response = restTemplate.exchange(url, HttpMethod.GET, request , Deck.class, id);
        return response.getBody();
    }

    public Deck GetDeckById(int id, String token)
    {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(httpHeaders);

        String url = "http://localhost:8083/api/private/deck/getByDeckId/{deckId}";
        ResponseEntity<Deck> response = restTemplate.exchange(url, HttpMethod.GET, request , Deck.class, id);
        return response.getBody();
    }
}
