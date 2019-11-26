package com.eternitywars.Models;

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

    public void setUsers(List<User> users)
    {
        this.users = users;
    }
}
