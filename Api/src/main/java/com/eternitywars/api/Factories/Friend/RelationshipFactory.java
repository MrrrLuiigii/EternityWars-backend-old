package com.eternitywars.api.Factories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipSqlContext;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class RelationshipFactory
{
    public RelationshipSqlContext getRelationshipSqlContext()
    {
        return new RelationshipSqlContext(new DatabaseConnection());
    }

    public RelationshipSqlContext getTestRelationshipSqlContext()
    {
        return new RelationshipSqlContext(new TestDatabaseConnection());
    }
}
