package com.eternitywars.Logic.Friend;

import com.eternitywars.Models.Account;
import com.eternitywars.Models.Friend;
import com.eternitywars.Models.FriendCollection;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class FriendContainerLogic
{
    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

    public FriendCollection GetAllFriends(JSONObject message){
        String json = message.getJSONObject("Content").toString();
        String token = message.getString("Token");

        User user = gson.fromJson(json, User.class);

        System.out.println(token);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<FriendCollection> response = restTemplate.exchange("http://localhost:8083/api/private/friend/get/{id}", HttpMethod.GET, request , FriendCollection.class, user.getId());
        //todo get all friends stuff
        return response.getBody();
    }

    public void RemoveFriend(int accountId){
        //todo remove friend stuff
    }
}
