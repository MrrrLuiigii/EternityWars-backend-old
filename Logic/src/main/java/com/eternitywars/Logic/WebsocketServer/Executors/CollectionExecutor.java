package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Collection.CollectionLogic;
import com.eternitywars.Models.Account;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class CollectionExecutor implements IExecutor  {


    private JSONObject message;
    private Session session;

    private CollectionLogic collectionLogic = new CollectionLogic();

    @Override
    public void Execute(JSONObject message, Session session) {
        switch (message.getString("Action")) {
            case "GETALLCARDSBYACCOUNT":
                collectionLogic.GetAllCardsByAccount(message);
        }
    }

    public CollectionExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void run() {
        Execute(message, session);
    }
}
