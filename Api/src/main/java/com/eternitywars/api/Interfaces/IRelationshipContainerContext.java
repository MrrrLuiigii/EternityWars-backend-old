package com.eternitywars.api.Interfaces;

import com.eternitywars.api.Models.RelationshipCollection;

public interface IRelationshipContainerContext
{
    RelationshipCollection GetRelationships(int userId);
}
