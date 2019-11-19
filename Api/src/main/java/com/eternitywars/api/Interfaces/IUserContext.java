package com.eternitywars.api.Interfaces;

import com.eternitywars.api.Models.User;

public interface IUserContext
{
    User GetUserById(int userId);
}
