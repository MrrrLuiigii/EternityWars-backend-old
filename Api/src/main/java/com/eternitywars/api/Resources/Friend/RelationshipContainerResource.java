package com.eternitywars.api.Resources.Friend;

import com.eternitywars.api.DAL.Repositories.Friend.RelationshipContainerRepository;
import com.eternitywars.api.Models.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/friend")
public class RelationshipContainerResource
{
    private RelationshipContainerRepository relationshipContainerRepository = new RelationshipContainerRepository();

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public boolean AddRelationship(@RequestBody Relationship relationship)
    {
        return relationshipContainerRepository.AddRelationship(relationship);
    }

    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public boolean DeleteRelationship(@RequestBody Relationship relationship)
    {
        return relationshipContainerRepository.DeleteRelationship(relationship);
    }

    @GetMapping(value = "/get/{userId}", consumes = "application/json", produces = "application/json")
    public RelationshipCollection GetRelationshipByUserId(@PathVariable("userId") int userId)
    {
        User user = new User(userId);
        return relationshipContainerRepository.GetRelationships(user);
    }
}