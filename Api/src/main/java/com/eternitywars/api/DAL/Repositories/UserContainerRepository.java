package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.UserContainerContext;
import com.eternitywars.api.Interfaces.IUserContainerContext;
import com.eternitywars.api.Models.User;

import java.util.List;

public class UserContainerRepository
{
    private IUserContainerContext userContainerContext;
    public UserContainerRepository()
    {
           userContainerContext = new UserContainerContext();
    }

    public List<User> GetUsers()
    {
          return userContainerContext.GetUsers();
    }

}
