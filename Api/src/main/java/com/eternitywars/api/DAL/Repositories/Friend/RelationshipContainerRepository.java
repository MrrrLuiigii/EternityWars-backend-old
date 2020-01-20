package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.Factories.Friend.RelationshipContainerFactory;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContainerContext;
import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

public class RelationshipContainerRepository implements IRelationshipContainerContext
{
    private IRelationshipContainerContext relationshipContainerContext;

    public RelationshipContainerRepository()
    {
        RelationshipContainerFactory relationshipFactory = new RelationshipContainerFactory();
        this.relationshipContainerContext = relationshipFactory.getRelationshipContainerSqlContext();
    }

    public RelationshipContainerRepository(RelationshipContainerFactory relationshipFactory)
    {
        this.relationshipContainerContext = relationshipFactory.getTestRelationshipContainerSqlContext();
    }

    public boolean AddRelationship(Relationship relationship)
    {
        return relationshipContainerContext.AddRelationship(relationship);
    }

    public boolean DeleteRelationship(Relationship relationship)
    {
        return relationshipContainerContext.DeleteRelationship(relationship);
    }

    public RelationshipCollection GetRelationships(User user )
    {
        return relationshipContainerContext.GetRelationships(user);
    }
}
