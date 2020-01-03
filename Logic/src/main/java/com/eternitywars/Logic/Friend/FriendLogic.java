package com.eternitywars.Logic.Friend;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Models.Enums.FriendStatus;
import com.eternitywars.Models.Relationship;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class FriendLogic
{
    private RestTemplate restTemplate = new RestTemplate();



    public void BlockFriend(JSONObject jsonObject)
    {
        String token = jsonObject.getString("Token");
        Relationship relationship = CreateRelationship(jsonObject, FriendStatus.Blocked);
        String url = "http://localhost:8083/api/private/friend/update";

        CreateRequest(relationship, token, url);
    }

    public void AcceptFriend(JSONObject jsonObject)
    {
        String token = jsonObject.getString("Token");
        Relationship relationship = CreateRelationship(jsonObject, FriendStatus.Accepted);
        String url = "http://localhost:8083/api/private/friend/update";

        CreateRequest(relationship, token, url);
    }

    private Relationship CreateRelationship(JSONObject jsonObject, FriendStatus friendStatus)
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        //Get pojo's from the jsonObject
        JSONObject content = jsonObject.getJSONObject("Content");
        User user = gson.fromJson(content.getJSONObject("user").toString(), User.class);
        User friend = gson.fromJson(content.getJSONObject("friend").toString(), User.class);

        return new Relationship(user.getUserId(), friend.getUserId(), friendStatus);
    }

    private void CreateRequest(Relationship relationship, String token, String url)
    {
        //Create the request to API with the generated Relationship object
        JSONObject output = new JSONObject(relationship);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(output.toString(), headers);

        restTemplate.postForObject(url, request, String.class);
    }
}
