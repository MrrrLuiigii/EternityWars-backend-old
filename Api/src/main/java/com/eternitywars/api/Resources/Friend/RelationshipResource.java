package com.eternitywars.api.Resources.Friend;

import com.eternitywars.api.DAL.Repositories.Friend.RelationshipRepository;
import com.eternitywars.api.Models.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/friend")
public class RelationshipResource
{
    private RelationshipRepository relationshipRepository = new RelationshipRepository();

    @PostMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public boolean UpdateRelationship(@RequestBody Relationship relationship)
    {
        return relationshipRepository.UpdateRelationship(relationship);
    }
}