package com.eternitywars.api.Models;

import java.util.ArrayList;
import java.util.List;

public class UserCollection
{
    private List<User> users;

    public UserCollection()
    {
        users = new ArrayList<>();
    }

    public List<User> getUsers()
    {
        return users;
    }

    public void addUser(User user)
    {
        users.add(user);
    }
}
