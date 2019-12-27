package com.eternitywars.api.Resources.Friend;

import com.eternitywars.api.DAL.Repositories.Friend.RelationshipRepository;
import com.eternitywars.api.Models.*;
import com.eternitywars.api.Models.Enums.FriendStatus;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/public/friend")
public class RelationshipResource
{
    private RelationshipRepository relationshipRepository = new RelationshipRepository();

    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public boolean UpdateRelationship(@RequestBody String json)
    {
        JSONObject jsonObject = new JSONObject(json);
        int userId = jsonObject.getInt("userId");
        int friendId = jsonObject.getInt("friendId");
        String status = jsonObject.getString("friendStatus");

        FriendStatus friendStatus = FriendStatus.Pending;
        if (status.equals("Accepted"))
        {
            friendStatus = FriendStatus.Accepted;
        }
        else if (status.equals("Blocked"))
        {
            friendStatus = FriendStatus.Blocked;
        }

        Relationship relationship = new Relationship(userId, friendId, friendStatus);

        return relationshipRepository.UpdateRelationship(relationship);
    }
}