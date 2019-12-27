package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Game.GameLogic;
import com.eternitywars.Models.Account;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class GameExecutor implements IExecutor  {

    GameLogic gameLogic = new GameLogic();

    @Override
    public void Execute(JSONObject message, Session session) {
        switch (message.getString("Action")) {
            case "LAUNCHGAME":
                Gson gson = new Gson();
                String json = message.getJSONObject("Content").toString();
                Account account = gson.fromJson(json, Account.class);
                System.out.println(account);
                break;
            case "RECONNECT":
                break;
            case "LEAVEGAME":
                break;
            case "ADDGAME":
                break;
            case "GETGAMEBYID":
                break;
            case "DELETEGAME":
                break;
        }
    }
}
