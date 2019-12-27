package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Friend.FriendContainerLogic;
import com.eternitywars.Logic.Friend.FriendLogic;
import com.eternitywars.Models.Account;
import com.google.gson.Gson;
import com.eternitywars.Models.FriendCollection;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class FriendExecutor implements IExecutor  {

    private FriendContainerLogic friendContainerLogic = new FriendContainerLogic();
    private FriendLogic friendLogic = new FriendLogic();

    private JSONObject message;
    private Session session;

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

    public FriendExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void run() {
        Execute(message, session);
    }
}
