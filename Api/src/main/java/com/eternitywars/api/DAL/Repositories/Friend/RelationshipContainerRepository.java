package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipContainerSqlContext;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContainerContext;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

public class RelationshipContainerRepository implements IRelationshipContainerContext
{
    private IRelationshipContainerContext relationshipContext;

    public RelationshipContainerRepository()
    {
        relationshipContext = new RelationshipContainerSqlContext();
    }



    public RelationshipCollection GetRelationships(User user )
    {
        return relationshipContext.GetRelationships(user);
    }
}
