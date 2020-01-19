package com.eternitywars.Models;

import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.Enums.FriendStatus;
import org.eclipse.jetty.websocket.api.Session;

public class Friend extends Account
{
    private FriendStatus friendStatus;

    

    public Friend(){}

    public Friend(int id, String username, AccountStatus accountStatus, Session session, FriendStatus friendStatus)
    {
        super(id,username, accountStatus, session);
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
