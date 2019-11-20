package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.RelationshipRepository;
import com.eternitywars.api.Models.RelationshipCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/relationships", method = RequestMethod.GET)
public class RelationshipResource
{
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public RelationshipCollection GetUserById(@PathVariable("userId")int userId)
    {
        RelationshipRepository relationshipRepository = new RelationshipRepository();
        return relationshipRepository.GetRelationships(userId);
    }
}