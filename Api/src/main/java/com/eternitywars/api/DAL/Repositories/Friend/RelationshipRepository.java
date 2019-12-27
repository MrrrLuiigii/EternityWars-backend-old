package com.eternitywars.api.DAL.Repositories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipSqlContext;
import com.eternitywars.api.Interfaces.Friend.IRelationshipContext;
import com.eternitywars.api.Models.Enums.FriendStatus;
import com.eternitywars.api.Models.Friend;
import com.eternitywars.api.Models.Relationship;
import com.eternitywars.api.Models.User;

public class RelationshipRepository implements IRelationshipContext
{
    private IRelationshipContext relationshipContext = new RelationshipSqlContext();

    public Relationship UpdateRelationship(Relationship relationship)
    {
        return relationshipContext.UpdateRelationship(relationship);
    }
}
