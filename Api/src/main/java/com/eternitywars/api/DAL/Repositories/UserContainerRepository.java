package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.UserContainerContext;
import com.eternitywars.api.Interfaces.IUserContainerContext;
import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;

import java.util.List;

public class UserContainerRepository implements IUserContainerContext
{
    private IUserContainerContext userContainerContext;
    public UserContainerRepository()
    {
           userContainerContext = new UserContainerContext();
    }

    public User GetUserById(int userId)
    {
        return userContainerContext.GetUserById(userId);
    }

    public UserCollection GetUsers()
    {
          return userContainerContext.GetUsers();
    }

}
