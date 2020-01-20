package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.Factories.Friend.RelationshipFactory;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContext;
import com.eternitywars.api.Models.Relationship;

public class RelationshipRepository implements IRelationshipContext
{
    private IRelationshipContext relationshipContext;

    public RelationshipRepository()
    {
        RelationshipFactory relationshipFactory = new RelationshipFactory();
        this.relationshipContext = relationshipFactory.getRelationshipSqlContext();
    }

    public RelationshipRepository(RelationshipFactory relationshipFactory)
    {
        this.relationshipContext = relationshipFactory.getTestRelationshipSqlContext();
    }

    public boolean UpdateRelationship(Relationship relationship)
    {
        return relationshipContext.UpdateRelationship(relationship);
    }
}
