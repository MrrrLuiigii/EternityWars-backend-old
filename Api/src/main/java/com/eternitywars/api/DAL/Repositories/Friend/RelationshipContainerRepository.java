package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipContainerContext;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContainerContext;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

public class RelationshipContainerRepository implements IRelationshipContainerContext
{
    private IRelationshipContainerContext relationshipContext;

    public RelationshipContainerRepository()
    {
        relationshipContext = new RelationshipContainerContext();
    }



    public RelationshipCollection GetRelationships(User user )
    {
        return relationshipContext.GetRelationships(user);
    }
}
