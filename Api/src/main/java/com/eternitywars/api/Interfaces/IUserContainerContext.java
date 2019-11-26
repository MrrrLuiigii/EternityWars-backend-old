package com.eternitywars.api.Interfaces;

import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;

public interface IUserContainerContext
{
    User GetUserById(int userId);
    UserCollection GetUsers();
    User AddUser(User user);
}
