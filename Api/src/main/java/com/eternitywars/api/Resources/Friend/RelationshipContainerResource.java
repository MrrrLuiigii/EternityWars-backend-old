package com.eternitywars.api.Resources.Friend;

import com.eternitywars.api.DAL.Repositories.Friend.RelationshipContainerRepository;
import com.eternitywars.api.Models.*;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/friend")
public class RelationshipContainerResource
{
    private RelationshipContainerRepository relationshipContainerRepository = new RelationshipContainerRepository();

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public boolean AddRelationship(@RequestBody String json)
    {
        JSONObject jsonObject = new JSONObject(json);
        int userId = jsonObject.getInt("userId");
        int friendId = jsonObject.getInt("friendId");

        return relationshipContainerRepository.AddRelationship(new User(userId), new Friend(friendId));
    }

    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public boolean DeleteRelationship(@RequestBody String json)
    {
        JSONObject jsonObject = new JSONObject(json);
        int userId = jsonObject.getInt("userId");
        int friendId = jsonObject.getInt("friendId");

        return relationshipContainerRepository.DeleteRelationship(new User(userId), new Friend(friendId));
    }

    @GetMapping(value = "/get/{userId}")
    public RelationshipCollection GetRelationshipCollectionByUserId(@PathVariable("userId")int userId)
    {
        User user = new User(userId);
        return relationshipContainerRepository.GetRelationships(user);
    }
}