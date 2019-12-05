package com.eternitywars.api.Interfaces.Friend;

import com.eternitywars.api.Models.RelationshipCollection;

public interface IRelationshipContainerContext
{
    RelationshipCollection GetRelationships(int userId);
}
