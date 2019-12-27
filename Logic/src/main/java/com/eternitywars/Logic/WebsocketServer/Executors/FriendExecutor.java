package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Friend.FriendContainerLogic;
import com.eternitywars.Logic.Friend.FriendLogic;
import com.eternitywars.Models.Account;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class FriendExecutor implements IExecutor  {

    FriendLogic friendLogic = new FriendLogic();
    FriendContainerLogic friendContainerLogic = new FriendContainerLogic();

    @Override
    public void Execute(JSONObject message, Session session) {
        switch (message.getString("Action")) {
            case "INVITE":
                Gson gson = new Gson();
                String json = message.getJSONObject("Content").toString();
                Account account = gson.fromJson(json, Account.class);
                System.out.println(account);
                break;
            case "ACCEPTREQUEST":
                break;
            case "REJECTREQUEST":
                break;
            case "GETALLFRIENDS":
                break;
            case "REMOVEFRIEND":
                break;
        }
    }
}
