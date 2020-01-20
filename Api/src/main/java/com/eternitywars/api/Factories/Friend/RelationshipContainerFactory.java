package com.eternitywars.api.Factories.Friend;

import com.eternitywars.api.DAL.Contexts.Friend.RelationshipContainerSqlContext;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class RelationshipContainerFactory
{
    public RelationshipContainerSqlContext getRelationshipContainerSqlContext()
    {
        return new RelationshipContainerSqlContext(new DatabaseConnection());
    }

    public RelationshipContainerSqlContext getTestRelationshipContainerSqlContext()
    {
        return new RelationshipContainerSqlContext(new TestDatabaseConnection());
    }
}
