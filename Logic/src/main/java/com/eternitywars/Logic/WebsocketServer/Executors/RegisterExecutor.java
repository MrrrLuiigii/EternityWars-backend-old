package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.WebsocketServer.Collection.UserCollection;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class RegisterExecutor implements IExecutor {

    private Gson gson = new Gson();

    @Override
    public void Execute(JSONObject message, Session session) {
        String json = message.getJSONObject("Content").toString();
        User user = gson.fromJson(json, User.class);
        user.setSession(session);
        UserCollection.getConnectedUsers().add(user);
    }
}
