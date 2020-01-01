package com.eternitywars.Logic.Friend;

import com.eternitywars.Models.*;
import com.eternitywars.Models.Enums.FriendStatus;
import com.google.gson.Gson;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class FriendContainerLogic
{
    private RestTemplate restTemplate = new RestTemplate();
    private Gson gson = new Gson();

//TODO get the friends out of this relationship
    public FriendCollection GetAllFriends(JSONObject message)
    {
        String json = message.getJSONObject("Content").toString();
        String token = message.getString("Token");

        User user = gson.fromJson(json, User.class);

        System.out.println(token);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<RelationshipCollection> response = restTemplate.exchange("http://localhost:8083/api/private/friend/get/{id}", HttpMethod.GET, request , RelationshipCollection.class, user.getUserId());

        //<editor-fold desc="Filter relationshipcollection and make it into a friendcollection">
        RelationshipCollection relationshipCollection = response.getBody();
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
                friend.setUserId(r.getFriendTwoId());
                friend.setUsername(r.getFriendOneUsername());
                friend.setAccountStatus(r.getFriendTwoAccountStatus());
                friend.setFriendStatus(FriendStatus.Requested);
                friendCollection.AddFriend(friend);
            }

            if (r.getFriendStatus() == FriendStatus.Blocked && r.getFriendTwoId() == user.getUserId())
            {
                // todo ik ben geblokkeerd door hem, hoe maken we hier onderscheid in betreft enum? een extra enum waarde maken?
                // is nodig omdat ik geen contact met hem mag leggen als ik geblokkeerd ben.
                // ik heb nu geïmplementeerd met extra enum waarde, als iemand het er niet mee eens is pas je het maar aan.

                Friend friend = new Friend();
                friend.setUserId(r.getFriendOneId());
                friend.setUsername(r.getFriendOneUsername());
                friend.setFriendStatus(FriendStatus.BlockedMe);
            }
        }
        //</editor-fold>

        return friendCollection;
    }

    public void RemoveFriend(int accountId){
        //todo remove friend stuff
    }
}
