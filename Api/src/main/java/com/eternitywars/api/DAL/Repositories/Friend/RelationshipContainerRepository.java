package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipContainerSqlContext;
import com.eternitywars.api.DAL.Contexts.Friend.RelationshipSqlContext;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContainerContext;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContext;
import com.eternitywars.api.Models.Friend;
import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.RelationshipCollection;
import com.eternitywars.api.Models.User;

public class RelationshipContainerRepository implements IRelationshipContainerContext
{
    private IRelationshipContainerContext relationshipContainerContext = new RelationshipContainerSqlContext();



    public boolean AddRelationship(User user, Friend friend)
    {
        return relationshipContainerContext.AddRelationship(user, friend);
    }

    public boolean DeleteRelationship(User user, Friend friend)
    {
        return relationshipContainerContext.DeleteRelationship(user, friend);
    }

    public RelationshipCollection GetRelationships(User user )
    {
        return relationshipContainerContext.GetRelationships(user);
    }
}
