package com.eternitywars.api.Interfaces.Friend;

import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

public interface IRelationshipContainerContext
{
    boolean AddRelationship(Relationship relationship);

    boolean DeleteRelationship(Relationship relationship);

    RelationshipCollection GetRelationships(User user);
}
