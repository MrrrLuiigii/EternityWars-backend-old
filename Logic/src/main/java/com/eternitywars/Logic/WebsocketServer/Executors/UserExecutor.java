package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsMessage;
import com.eternitywars.Models.MockUser;
import com.eternitywars.Models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class UserExecutor implements IExecutor{

    private UserContainerLogic userContainerLogic = new UserContainerLogic();

    public void Execute(JSONObject message, Session session) {
        switch (message.getString("Action")) {
            case "GETBYID":
                Gson gson = new Gson();
                String json = message.getJSONObject("Content").toString();
                User user = gson.fromJson(json, User.class);
                userContainerLogic.AddUserByUsernameAndEmail(user);
                System.out.println(user);
                break;
        }
    }
}
