package com.eternitywars.Logic.WebsocketServer.Collection;

import com.eternitywars.Models.User;
import org.eclipse.jetty.websocket.api.Session;

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

    public static boolean CheckNotRegistered(User user){

        for (User u : ConnectedUsers){
            if(u.getUsername().equals(user.getUsername())){
                return false;
            }
        }
        return true;
    }

    public static User getUserBySession(Session session) {
        for (User u : ConnectedUsers) {
            if (u.getSession() == session) {
                return u;
            }
        }
        return null;
    }
}
