package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.Enums.FriendStatus;

public class Relationship
{
    private int friendOneId;
    private String friendOneUsername;
    private AccountStatus friendOneAccountStatus;

    private int friendTwoId;
    private String friendTwoUsername;
    private AccountStatus friendTwoAccountStatus;

    private FriendStatus friendStatus;

    public Relationship(){}

    public Relationship(int friendOneId, int friendTwoId, FriendStatus friendStatus)
    {
        this.friendOneId = friendOneId;
        this.friendTwoId = friendTwoId;
        this.friendStatus = friendStatus;
    }

    public Relationship(int friendOneId, String friendOneUsername, AccountStatus friendOneAccountStatus,
                        int friendTwoId, String friendTwoUsername, AccountStatus friendTwoAccountStatus,
                        FriendStatus friendStatus)
    {
        this.friendOneId = friendOneId;
        this.friendOneUsername = friendOneUsername;
        this.friendOneAccountStatus = friendOneAccountStatus;
        this.friendTwoId = friendTwoId;
        this.friendTwoUsername = friendTwoUsername;
        this.friendTwoAccountStatus = friendTwoAccountStatus;
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

    public String getFriendOneUsername()
    {
        return friendOneUsername;
    }

    public void setFriendOneUsername(String friendOneUsername)
    {
        this.friendOneUsername = friendOneUsername;
    }

    public AccountStatus getFriendOneAccountStatus()
    {
        return friendOneAccountStatus;
    }

    public void setFriendOneAccountStatus(AccountStatus friendOneAccountStatus)
    {
        this.friendOneAccountStatus = friendOneAccountStatus;
    }

    public int getFriendTwoId()
    {
        return friendTwoId;
    }

    public void setFriendTwoId(int friendTwoId)
    {
        this.friendTwoId = friendTwoId;
    }

    public String getFriendTwoUsername()
    {
        return friendTwoUsername;
    }

    public void setFriendTwoUsername(String friendTwoUsername)
    {
        this.friendTwoUsername = friendTwoUsername;
    }

    public AccountStatus getFriendTwoAccountStatus()
    {
        return friendTwoAccountStatus;
    }

    public void setFriendTwoAccountStatus(AccountStatus friendTwoAccountStatus)
    {
        this.friendTwoAccountStatus = friendTwoAccountStatus;
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
