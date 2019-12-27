package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Shop.ShopLogic;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class ShopExecutor implements IExecutor {

    private ShopLogic shopLogic = new ShopLogic();

    @Override
    public void Execute(JSONObject message, Session session) {

    }
}
