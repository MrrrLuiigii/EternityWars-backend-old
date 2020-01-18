package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Shop.CardPickerLogic;
import com.eternitywars.Logic.utils.MessageSender;
import com.eternitywars.Models.Pack;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import com.eternitywars.Logic.Shop.ShopLogic;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;

public class ShopExecutor implements IExecutor{

    private ShopLogic shopLogic = new ShopLogic();

    private JSONObject message;
    private Session session;

    public ShopExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void Execute(JSONObject message, Session session) throws IOException {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();
        String json = message.getJSONObject("Content").toString();
        String token = message.getString("Token");
        User user = gson.fromJson(json, User.class);
        switch (message.getString("Action")) {
            case "BUYPACK":
                shopLogic.PurchaseSomePacks(user, message.getInt("Amount"), token);
                MessageSender.SendGenericMessageToUser(user, "BUYPACK", user);
                break;
            case "OPENPACK":
                Pack pack = shopLogic.OpenPack(user, token);
                MessageSender.SendGenericMessageToUser(pack, "OPENPACK", user);
                MessageSender.SendGenericMessageToUser(user, "UPDATEUSERINFO", user);
                break;
        }
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
