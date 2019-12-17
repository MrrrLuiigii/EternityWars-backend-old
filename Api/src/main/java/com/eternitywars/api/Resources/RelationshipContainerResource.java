package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.Friend.RelationshipContainerRepository;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/relationships")
public class RelationshipContainerResource
{
    RelationshipContainerRepository relationshipContainerRepository = new RelationshipContainerRepository();

    @GetMapping(value = "/get/{userId}")
    public RelationshipCollection GetUserById(@PathVariable("userId")int userId)
    {
        User user = new User(userId);
        return relationshipContainerRepository.GetRelationships(user);
    }
}