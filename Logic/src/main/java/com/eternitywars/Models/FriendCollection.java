package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class FriendCollection
{
    private List<Friend> friends;

    public FriendCollection()
    {
        this.friends = new ArrayList<>();
    }

    public List<Friend> getFriends()
    {
        return friends;
    }

    public void AddFriend(Friend friend)
    {
        this.friends.add(friend);
    }
}
