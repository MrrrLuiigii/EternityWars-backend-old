package com.eternitywars.api.Interfaces;

import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;

import java.util.List;

public interface IUserContainerContext
{
    User GetUserById(int userId);
    UserCollection GetUsers();
}
