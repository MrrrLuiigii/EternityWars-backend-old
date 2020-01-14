package com.eternitywars.Logic.utils;


import com.eternitywars.Logic.WebsocketServer.Collection.UserCollection;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.Lobby;
import com.eternitywars.Models.LobbyCollection;
import com.eternitywars.Models.User;

import java.io.IOException;

public class MessageSender {

    public static void UpdateParticipatingLobby(Lobby lobby, String action) throws IOException {

        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction(action);
        returnMessage.setContent(lobby);

        for (User u : UserCollection.getConnectedUsers()){
            if(lobby.getPlayerOne() != null){
                if(u.getUserId() == lobby.getPlayerOne().getUserId()){
                    u.getSession().getRemote().sendString(MessageConverter.FromObjectToString(returnMessage));
                }
            }
            if(lobby.getPlayerTwo() != null){
                if(u.getUserId() == lobby.getPlayerTwo().getUserId()){
                    u.getSession().getRemote().sendString(MessageConverter.FromObjectToString(returnMessage));
                }
            }
        }
    }

    public static void UpdateLobbyList(LobbyCollection lobbies) throws IOException {
        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction("GETLOBBIES");
        returnMessage.setContent(lobbies);


        for (User u : UserCollection.getConnectedUsers()) {
            System.out.println(u.getUsername());
            u.getSession().getRemote().sendString(MessageConverter.FromObjectToString(returnMessage));
        }
    }
}
