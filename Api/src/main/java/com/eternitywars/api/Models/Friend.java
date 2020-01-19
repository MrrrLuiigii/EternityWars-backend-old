package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.Enums.FriendStatus;

import javax.websocket.Session;
import java.net.Socket;

public class Friend extends Account
{
    private FriendStatus friendStatus;

    public Friend(){}

    public Friend(int friendId)
    {
        this.userId = friendId;
    }

    public Friend(int id, String username, AccountStatus accountStatus, Session session, FriendStatus friendStatus)
    {
        super(id, username, accountStatus, session);
        this.friendStatus = friendStatus;
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
