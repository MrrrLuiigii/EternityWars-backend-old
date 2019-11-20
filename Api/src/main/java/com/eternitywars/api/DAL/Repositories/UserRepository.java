package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.UserContext;
import com.eternitywars.api.Interfaces.IUserContext;
import com.eternitywars.api.Models.User;

public class UserRepository implements IUserContext
{
    private IUserContext userContext;

    public UserRepository()
    {
        userContext = new UserContext();
    }
}
