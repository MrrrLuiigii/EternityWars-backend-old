package com.eternitywars.Logic.Friend;

import com.eternitywars.Models.Relationship;
import com.eternitywars.Models.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class FriendLogic
{

    @Autowired
    private RestTemplate restTemplate;

    public void HandleFriendStatus(String json, String status){
        Relationship relationship = new Relationship();
        //take the token and data out of the json and put them in models
        JSONObject incommingobject = new JSONObject(json);
        relationship.setFriendOneId(incommingobject.getInt("userId"));
        relationship.setFriendTwoId(incommingobject.getInt("friendId"));

        String token = incommingobject.getString("Token");

        //serialize the model back to json
        JSONObject output = new JSONObject(relationship);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(output.toString(), headers);

        switch (status){
            case "Accept":
                restTemplate.postForObject("accept url", request, Relationship.class);
                break;
            case "Invite":
                restTemplate.postForObject("invite url", request, Relationship.class);
                break;
            case "Reject":
                restTemplate.postForObject("reject url", request, Relationship.class);
                break;
        }

    }

    public void RejectRequest(int accountId,int friendId){
        //todo accept request stuff(parameter names must be changed)
    }
}
