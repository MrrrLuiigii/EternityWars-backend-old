package com.eternitywars.api.Interfaces;

import com.eternitywars.api.Models.RelationshipCollection;

public interface IRelationshipContext
{
    RelationshipCollection GetRelationships(int userId);
}
