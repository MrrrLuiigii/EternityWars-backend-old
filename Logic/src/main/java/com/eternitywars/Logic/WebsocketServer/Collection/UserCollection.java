package com.eternitywars.Logic.WebsocketServer.Collection;

import com.eternitywars.Models.User;

import java.util.ArrayList;
import java.util.List;

public class UserCollection {

    public static List<User> getConnectedUsers() {
        return ConnectedUsers;
    }

    public static void setConnectedUsers(List<User> connectedUsers) {
        ConnectedUsers = connectedUsers;
    }

    private static List<User> ConnectedUsers = new ArrayList<>();

}
