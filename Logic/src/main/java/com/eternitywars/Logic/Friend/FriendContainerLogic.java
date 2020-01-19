package com.eternitywars.Logic.Friend;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Models.*;
import com.eternitywars.Models.Enums.FriendStatus;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class FriendContainerLogic
{
    private RestTemplate restTemplate = new RestTemplate();



    public void InviteFriend(JSONObject jsonObject)
    {
        String token = jsonObject.getString("Token");

        //Get the friend with it's username
        UserContainerLogic userContainerLogic = new UserContainerLogic();
        User friend = userContainerLogic.getUserByUsername(jsonObject.getString("friendname"), token);

        //Create a relationship with friend, user and status
        Relationship relationship = new Relationship();
        relationship.setFriendOneId(jsonObject.getJSONObject("Content").getInt("userId"));
        relationship.setFriendTwoId(friend.getUserId());
        relationship.setFriendStatus(FriendStatus.Pending);

        String url = "http://localhost:8083/api/private/friend/add";

        CreateUpdateRelationshipRequest(relationship, token, url);
    }

    public void RemoveFriend(JSONObject jsonObject)
    {
        String token = jsonObject.getString("Token");
        Relationship relationship = CreateRelationship(jsonObject, null);
        String url = "http://localhost:8083/api/private/friend/delete";

        CreateUpdateRelationshipRequest(relationship, token, url);
    }

    public FriendCollection GetAllFriends(User user, String token)
    {
        String url = "http://localhost:8083/api/private/friend/get/{id}";
        RelationshipCollection relationshipCollection = CreateGetRelationshipCollectionRequest(user, token, url);

        FriendCollection friendCollection = new FriendCollection();

        for (Relationship r : relationshipCollection.getRelationships())
        {
            if (r.getFriendOneId() == user.getUserId())
            {
                Friend friend = new Friend();
                friend.setUserId(r.getFriendTwoId());
                friend.setUsername(r.getFriendTwoUsername());
                friend.setAccountStatus(r.getFriendTwoAccountStatus());
                friend.setFriendStatus(r.getFriendStatus());
                friendCollection.AddFriend(friend);
            }

            if (r.getFriendStatus() == FriendStatus.Pending && r.getFriendTwoId() == user.getUserId())
            {
                Friend friend = new Friend();
                friend.setUserId(r.getFriendOneId());
                friend.setUsername(r.getFriendOneUsername());
                friend.setAccountStatus(r.getFriendOneAccountStatus());
                friend.setFriendStatus(FriendStatus.Requested);
                friendCollection.AddFriend(friend);
            }

            if (r.getFriendStatus() == FriendStatus.Blocked && r.getFriendTwoId() == user.getUserId())
            {
                Friend friend = new Friend();
                friend.setUserId(r.getFriendOneId());
                friend.setUsername(r.getFriendOneUsername());
                friend.setFriendStatus(FriendStatus.BlockedMe);
            }
        }

        return friendCollection;
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

    private void CreateUpdateRelationshipRequest(Relationship relationship, String token, String url)
    {
        //Create the request to API with the generated Relationship object
        JSONObject output = new JSONObject(relationship);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(output.toString(), headers);

        restTemplate.postForObject(url, request, String.class);
    }

    private RelationshipCollection CreateGetRelationshipCollectionRequest(User user, String token, String url)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<RelationshipCollection> response = restTemplate.exchange(url, HttpMethod.GET, request , RelationshipCollection.class, user.getUserId());

        return response.getBody();
    }
}
