package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Models.Account;
import com.google.gson.Gson;
import com.eternitywars.Logic.Shop.ShopLogic;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class ShopExecutor implements IExecutor {

    private ShopLogic shopLogic = new ShopLogic();

    @Override
    public void Execute(JSONObject message, Session session) {
        switch (message.getString("Action")) {
            case "BUYPACK":
                Gson gson = new Gson();
                String json = message.getJSONObject("Content").toString();
                Account account = gson.fromJson(json, Account.class);
                System.out.println(account);
                break;
            case "OPENPACK":
                break;
            case "PICKCARDS":
                break;
        }
    }
}
