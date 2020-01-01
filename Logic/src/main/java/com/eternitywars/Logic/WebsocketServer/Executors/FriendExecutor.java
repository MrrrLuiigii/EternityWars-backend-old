package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Friend.FriendContainerLogic;
import com.eternitywars.Logic.Friend.FriendLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.Account;
import com.eternitywars.Models.FriendCollection;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;

public class FriendExecutor implements IExecutor  {

    private FriendContainerLogic friendContainerLogic = new FriendContainerLogic();
    private FriendLogic friendLogic = new FriendLogic();

    private JSONObject message;
    private Session session;


    @Override
    public void Execute(JSONObject message, Session session) throws IOException {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();
        WsReturnMessage returnMessage = new WsReturnMessage();
        switch (message.getString("Action")) {
            case "INVITE":
                friendLogic.HandleFriendStatus(message, "Invite");
                break;
            case "ACCEPTREQUEST":
                friendLogic.HandleFriendStatus(message, "Accept");

                FriendCollection content2 = friendContainerLogic.GetAllFriends(message);
                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(content2);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
            case "REJECTREQUEST":
                friendLogic.HandleFriendStatus(message, "Reject");

                FriendCollection content1 = friendContainerLogic.GetAllFriends(message);
                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(content1);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
            case "GETALLFRIENDS":
                FriendCollection content = friendContainerLogic.GetAllFriends(message);

                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(content);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
            case "REMOVEFRIEND":
                break;
        }
    }

    public FriendExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void run() {
        try {
            Execute(message, session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
