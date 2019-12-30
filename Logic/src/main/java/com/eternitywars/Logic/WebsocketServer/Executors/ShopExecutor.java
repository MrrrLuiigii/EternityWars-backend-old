package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Shop.CardPickerLogic;
import com.eternitywars.Models.Account;
import com.eternitywars.Models.Pack;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import com.eternitywars.Logic.Shop.ShopLogic;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class ShopExecutor implements IExecutor{

    private ShopLogic shopLogic = new ShopLogic();
    private CardPickerLogic cardPickerLogic = new CardPickerLogic();

    private JSONObject message;
    private Session session;

    public ShopExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void Execute(JSONObject message, Session session) {
        Gson gson = new Gson();
        String json = message.getJSONObject("Content").toString();
        User user = gson.fromJson(json, User.class);
        switch (message.getString("Action")) {
            case "BUYPACK":
                shopLogic.BuyPack(user);
                break;
            case "OPENPACK":
                Pack pack = shopLogic.OpenPack(user);
                //todo return pack via ws
                break;
        }
    }

    @Override
    public void run() {
        Execute(message, session);
    }
}
