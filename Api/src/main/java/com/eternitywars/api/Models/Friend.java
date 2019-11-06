package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.Enums.FriendStatus;

import java.net.Socket;

public class Friend extends Account
{
    private FriendStatus friendStatus;

    public Friend(int id, String username, AccountStatus accountStatus, Socket socket, FriendStatus friendStatus)
    {
        super(id, username, accountStatus, socket);
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
