package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipSqlContext;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContext;
import com.eternitywars.api.Models.Relationship;

public class RelationshipRepository implements IRelationshipContext
{
    private IRelationshipContext relationshipContext = new RelationshipSqlContext();

    public boolean UpdateRelationship(Relationship relationship)
    {
        return relationshipContext.UpdateRelationship(relationship);
    }
}
