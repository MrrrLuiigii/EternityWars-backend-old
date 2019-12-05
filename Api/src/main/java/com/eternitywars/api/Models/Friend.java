package com.eternitywars.api.Models;

import com.eternitywars.api.Models.Enums.AccountStatus;
import com.eternitywars.api.Models.Enums.FriendStatus;
import java.net.Socket;

public class Friend extends Account
{
    private FriendStatus friendStatus;

    public Friend(int id, String googleId, String username, AccountStatus accountStatus, Socket socket, FriendStatus friendStatus)
    {
        super(id, googleId, username, accountStatus, socket);
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
