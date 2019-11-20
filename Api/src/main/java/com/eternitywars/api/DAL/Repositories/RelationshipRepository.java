package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.RelationshipContext;
import com.eternitywars.api.Interfaces.IRelationshipContext;
import com.eternitywars.api.Models.RelationshipCollection;

public class RelationshipRepository implements IRelationshipContext
{
    private IRelationshipContext relationshipContext;

    public RelationshipRepository()
    {
        relationshipContext = new RelationshipContext();
    }

    public RelationshipCollection GetRelationships(int userId){
        return relationshipContext.GetRelationships(userId);
    }
}
