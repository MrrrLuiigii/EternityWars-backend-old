package com.eternitywars.api.Interfaces.Friend;

import com.eternitywars.api.Models.Friend;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

public interface IRelationshipContainerContext
{
    boolean AddRelationship(User user, Friend friend);

    boolean DeleteRelationship(User user, Friend friend);

    RelationshipCollection GetRelationships(User user);
}
