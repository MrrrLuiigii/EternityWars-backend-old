package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class FriendCollection
{
    private List<Friend> friends;
    private List<Friend> blocked;
    private List<Friend> pending;

    public FriendCollection()
    {
        this.friends = new ArrayList<>();
        this.blocked = new ArrayList<>();
        this.pending = new ArrayList<>();
    }

    public List<Friend> getFriends()
    {
        return friends;
    }

    public void addFriend(Friend friend)
    {
        this.friends.add(friend);
    }

    public List<Friend> getBlocked()
    {
        return blocked;
    }

    public void addBlocked(Friend blocked)
    {
        this.blocked.add(blocked);
    }

    public List<Friend> getPending()
    {
        return pending;
    }

    public void addPending(Friend pending)
    {
        this.pending.add(pending);
    }
}
