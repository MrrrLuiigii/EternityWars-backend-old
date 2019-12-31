package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Friend.FriendContainerLogic;
import com.eternitywars.Logic.Friend.FriendLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.Account;
import com.eternitywars.Models.RelationshipCollection;
import com.google.gson.Gson;
import com.eternitywars.Models.FriendCollection;
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
                String json = message.getJSONObject("Content").toString();
                Account account = gson.fromJson(json, Account.class);
                System.out.println(account);
                friendLogic.HandleFriendStatus("json thing", "Invite");
                break;
            case "ACCEPTREQUEST":
                friendLogic.HandleFriendStatus(message.getJSONObject("Content").toString(), "Accept");
                break;
            case "REJECTREQUEST":
                friendLogic.HandleFriendStatus(message.getJSONObject("Content").toString(), "Reject");
                break;
            case "GETALLFRIENDS":
                RelationshipCollection content = friendContainerLogic.GetAllFriends(message);
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
