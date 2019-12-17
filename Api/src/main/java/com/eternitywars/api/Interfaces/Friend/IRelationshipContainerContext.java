package com.eternitywars.api.Interfaces.Friend;

import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

public interface IRelationshipContainerContext
{
    RelationshipCollection GetRelationships(User user);
}
