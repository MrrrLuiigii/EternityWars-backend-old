package com.eternitywars.api.DAL.Repositories.User;

import com.eternitywars.api.DAL.Contexts.User.UserSqlContext;
import com.eternitywars.api.Factories.User.UserFactory;
import com.eternitywars.api.Interfaces.User.IUserContext;
import com.eternitywars.api.Models.User;

public class UserRepository implements IUserContext
{
    private IUserContext userContext;

    public UserRepository()
    {
        UserFactory userFactory = new UserFactory();
        this.userContext = userFactory.getUserSqlContext();
    }

    public UserRepository(UserFactory userFactory)
    {
        this.userContext = userFactory.getTestUserSqlContext();
    }



    public boolean UpdateUsername(User user)
    {
        return userContext.UpdateUsername(user);
    }

    public boolean UpdateAccountStatus(User user)
    {
        return userContext.UpdateAccountStatus(user);
    }

    public boolean UpdatePackAmount(User user)
    {
        return userContext.UpdatePackAmount(user);
    }

    public boolean UpdateGold(User user){return userContext.UpdateGold(user);}
}
