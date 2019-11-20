package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.FriendStatus;

public class Relationship
{
    private int friendOneId;
    private int friendTwoId;
    private FriendStatus friendStatus;

    public Relationship(){}

    public Relationship(int friendOneId, int friendTwoId, FriendStatus friendStatus)
    {
        this.friendOneId = friendOneId;
        this.friendTwoId = friendTwoId;
        this.friendStatus = friendStatus;
    }

    public int getFriendOneId()
    {
        return friendOneId;
    }

    public void setFriendOneId(int friendOneId)
    {
        this.friendOneId = friendOneId;
    }

    public int getFriendTwoId()
    {
        return friendTwoId;
    }

    public void setFriendTwoId(int friendTwoId)
    {
        this.friendTwoId = friendTwoId;
    }

    public FriendStatus getFriendStatus()
    {
        return friendStatus;
    }

    public void setFriendStatus(FriendStatus friendStatus)
    {
        this.friendStatus = friendStatus;
    }
}
