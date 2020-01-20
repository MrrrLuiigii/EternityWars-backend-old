package com.eternitywars.api.Factories.User;

import com.eternitywars.api.DAL.Contexts.User.UserSqlContext;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class UserFactory
{
    public UserSqlContext getUserSqlContext()
    {
        return new UserSqlContext(new DatabaseConnection());
    }

    public UserSqlContext getTestUserSqlContext()
    {
        return new UserSqlContext(new TestDatabaseConnection());
    }
}
