package com.eternitywars.Logic.Game;

import com.eternitywars.Models.Deck;
import com.eternitywars.Models.Game;
import com.eternitywars.Models.Player;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class GameContainerLogic
{
    private RestTemplate restTemplate = new RestTemplate();

    public boolean AddGame(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("game").toString(), headers);
        restTemplate.postForObject("http://localhost:8083/api/private/game/add", request, boolean.class);
        return true;
    }

    public Game GetGameById(JSONObject jsonObject){
        String token = jsonObject.getString("Token");
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        JSONObject content = jsonObject.getJSONObject("Content");
        Game game = gson.fromJson(content.getJSONObject("game").toString(), Game.class);
        HttpEntity<String> request = new HttpEntity<>(content.getJSONObject("game").toString(), headers);
        ResponseEntity<Game> response = restTemplate.exchange("http://localhost:8083/api/private/deck/get/{gameID}", HttpMethod.GET, request , Game.class);
        return response.getBody();
    }

    public List<Game> GetGames(){
        return null;
    }

    public boolean DeleteGame(Game game){
        //todo delete game stuff
        return true;
    }

}
