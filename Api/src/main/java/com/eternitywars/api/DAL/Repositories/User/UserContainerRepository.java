package com.eternitywars.api.DAL.Repositories.User;

import com.eternitywars.api.DAL.Contexts.User.UserContainerContext;
import com.eternitywars.api.Interfaces.User.IUserContainerContext;
import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;

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

    public User GetUserByEmail(String userEmail)
    {
        return userContainerContext.GetUserByEmail(userEmail);
    }

    public UserCollection GetUsers()
    {
          return userContainerContext.GetUsers();
    }

    public User AddUser(User user)
    {
        return userContainerContext.AddUser(user);
    }

}
