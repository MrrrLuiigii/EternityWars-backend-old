package com.eternitywars.api.DAL.Repositories.User;

import com.eternitywars.api.DAL.Contexts.User.UserContext;
import com.eternitywars.api.Interfaces.User.IUserContext;
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

    public void UpdatePackAmount(User user){ userContext.UpdatePackAmount(user);}

    public void UpdateGold(User user){ userContext.UpdateGold(user);}
}
