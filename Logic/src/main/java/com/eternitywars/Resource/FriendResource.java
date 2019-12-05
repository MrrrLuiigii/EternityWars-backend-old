package com.eternitywars.Resource;

import com.eternitywars.Models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/friend", method = RequestMethod.GET)
public class FriendResource
{
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public FriendCollection GetFriendsByUserId(@PathVariable("userId")int userId)
    {
        FriendCollection friendCollection = new FriendCollection();

        RelationshipCollection relationshipCollection =
                restTemplate.getForObject("http://eternity-wars-api/api/private/relationships/get"
                        + userId, RelationshipCollection.class);

        for (Relationship r : relationshipCollection.getRelationships())
        {
            if (r.getFriendOneId() == userId)
            {

            }
        }

        return null;
    }
}
