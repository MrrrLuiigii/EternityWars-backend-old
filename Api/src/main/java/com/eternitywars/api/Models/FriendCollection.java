package com.eternitywars.api.Models;

import java.util.List;

public class FriendCollection
{
    private List<Friend> friends;

    public FriendCollection(){}

    public FriendCollection(List<Friend> friends)
    {
        this.friends = friends;
    }

    public List<Friend> getFriends()
    {
        return friends;
    }

    public void setFriends(List<Friend> friends)
    {
        this.friends = friends;
    }
}
