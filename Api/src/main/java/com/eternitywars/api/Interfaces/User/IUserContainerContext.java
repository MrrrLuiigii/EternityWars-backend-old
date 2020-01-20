package com.eternitywars.api.Interfaces.User;

import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;

public interface IUserContainerContext
{
    User GetUserById(int userId);

    User GetUserByUsername(String username);

    User GetUserByEmail(String userEmail);

    UserCollection GetUsers();

    User AddUser(User user);

    boolean DeleteUser(User user);
}
