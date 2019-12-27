package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Collection.CollectionLogic;
import com.eternitywars.Models.Account;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class CollectionExecutor implements IExecutor  {

    CollectionLogic collectionLogic = new CollectionLogic();

    @Override
    public void Execute(JSONObject message, Session session) {
        switch (message.getString("Action")) {
            case "GETALLCARDSBYACCOUNT":
                Gson gson = new Gson();
                String json = message.getJSONObject("Content").toString();
                Account account = gson.fromJson(json, Account.class);
                collectionLogic.GetAllCardsByAccount(account);
                System.out.println(account);
                break;
            case "ADDCARDTOCARDCOLLECTION":
                break;
            case "GETALLDECKBYACCOUNT":
                break;
            case "ADDDECKTODECKCOLLECTION":
                break;
            case "REMOVEDECKFROMDECKCOLLECTION":
                break;

        }
    }
}
