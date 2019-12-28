package com.eternitywars.Logic.Friend;

import com.eternitywars.Models.Enums.FriendStatus;
import com.eternitywars.Models.FriendCollection;
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
    public void HandleFriendStatus(String json, String status){
        Relationship relationship = new Relationship();
        //take the token and data out of the json and put them in models
        JSONObject incommingobject = new JSONObject(json);
        relationship.setFriendOneId(incommingobject.getInt("userId"));
        relationship.setFriendTwoId(incommingobject.getInt("friendId"));
        
//        String token = incommingobject.getString("Token");
        
        switch (status){
            case "Accept":
                relationship.setFriendStatus(FriendStatus.Accepted);
                break;
            case "Invite":
                relationship.setFriendStatus(FriendStatus.Pending);
                break;
            case "Blocked":
                relationship.setFriendStatus(FriendStatus.Blocked);
                break;
        }
        
        //serialize the model back to json
        JSONObject output = new JSONObject(relationship);
        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(output.toString(), headers);

        RestTemplate restTemplate = new RestTemplate();

        restTemplate.postForObject("http://localhost:8083/api/public/friend/update", request, String.class);
    }

    public FriendCollection GetRelationschipsByUserId(String json)
    {
        FriendCollection friends = new FriendCollection();
        JSONObject incommingobject = new JSONObject(json);

        return friends;
    }
}
