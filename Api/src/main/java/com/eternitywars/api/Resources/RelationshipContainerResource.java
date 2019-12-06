package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.Friend.RelationshipContainerRepository;
import com.eternitywars.api.Models.RelationshipCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/relationships", method = RequestMethod.GET)
public class RelationshipContainerResource
{
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public RelationshipCollection GetUserById(@PathVariable("userId")int userId)
    {
        RelationshipContainerRepository relationshipRepository = new RelationshipContainerRepository();
        return relationshipRepository.GetRelationships(userId);
    }
}