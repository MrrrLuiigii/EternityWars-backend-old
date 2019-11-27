package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.UserContext;
import com.eternitywars.api.Interfaces.IUserContext;
import com.eternitywars.api.Models.User;

public class UserRepository
{
    private IUserContext userContext;

    public UserRepository()
    {
        this.userContext = new UserContext();
    }

    public void ChangeUsername(int userId, String username){
        userContext.ChangeUserName( userId, username);
    }
}
