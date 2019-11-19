package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.UserMySQLContext;
import com.eternitywars.api.Interfaces.IUserContext;
import com.eternitywars.api.Models.User;

public class UserRepository
{
    private IUserContext userContext;

    public UserRepository()
    {
        userContext = new UserMySQLContext();
    }

    public User GetUserById(int userId){
        return userContext.GetUserById(userId);
    }
}
