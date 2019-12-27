package com.eternitywars.Logic.WebsocketServer.MessageHandler;

import com.eternitywars.Logic.WebsocketServer.Executors.UserExecutor;
import com.eternitywars.Logic.WebsocketServer.Models.WsMessage;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class MessageHandler {

    private UserExecutor userExecutor = new UserExecutor();

    public void handleMessage(Session session, JSONObject message) {
        switch (message.getString("Subject")) {
            case "USER":
                userExecutor.Execute(message, session);
                break;
        }
    }
}
