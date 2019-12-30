package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Logic.User.UserLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsMessage;
import com.eternitywars.Models.MockUser;
import com.eternitywars.Models.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import sun.nio.cs.US_ASCII;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;

public class UserExecutor implements IExecutor{

    private UserContainerLogic userContainerLogic = new UserContainerLogic();
    private UserLogic userLogic = new UserLogic();
    private Gson gson = new Gson();

    private JSONObject message;
    private Session session;

    public UserExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void Execute(JSONObject message, Session session) throws IOException {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();
        String json;
        switch (message.getString("Action")) {
            case "GetUserById":
                json = message.getJSONObject("Content").toString();
                //todo gson heeft geen zin in
                User user = gson.fromJson(message.getJSONObject("Content").toString(), User.class);
                userContainerLogic.GetUserById(user);
                System.out.println(user);
                break;
            case "GetUserByEmail":
                user = userContainerLogic.GetUserByEmail(message);
                session.getRemote().sendString(gson.toJson(user));
                break;
            case "GetUsers":

                break;
            case "AddUser":

                break;
            case "UpdateUsername":

                break;
            case "UpdateAccountStatus":

                break;
            case "UpdatePackAmount":

                break;

        }
    }

    @Override
    public void run() {
        try {
            synchronized (message){
                Execute(message, session);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
