package com.eternitywars.api.Factories.User;

import com.eternitywars.api.DAL.Contexts.User.UserContainerSqlContext;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class UserContainerFactory
{
    public UserContainerSqlContext getUserContainerSqlContext()
    {
        return new UserContainerSqlContext(new DatabaseConnection());
    }

    public UserContainerSqlContext getTestUserContainerSqlContext()
    {
        return new UserContainerSqlContext(new TestDatabaseConnection());
    }
}
