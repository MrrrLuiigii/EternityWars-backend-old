package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.RelationshipContainerContext;
import com.eternitywars.api.Interfaces.IRelationshipContainerContext;
import com.eternitywars.api.Models.RelationshipCollection;

public class RelationshipContainerRepository implements IRelationshipContainerContext
{
    private IRelationshipContainerContext relationshipContext;

    public RelationshipContainerRepository()
    {
        relationshipContext = new RelationshipContainerContext();
    }

    public RelationshipCollection GetRelationships(int userId){
        return relationshipContext.GetRelationships(userId);
    }
}
