package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Logic.User.UserLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

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
        User user = gson.fromJson(message.getJSONObject("Content").toString(), User.class);
        String token = message.getString("Token");
        switch (message.getString("Action")) {
            case "GetUserById":
                json = message.getJSONObject("Content").toString();
                //todo gson heeft geen zin in

                userContainerLogic.GetUserById(user);
                System.out.println(user);
                break;
            case "GetUserByEmail":
                WsReturnMessage returnMessage = new WsReturnMessage();
                returnMessage.setAction("GetUserByEmail");
                user = userContainerLogic.GetUserByEmail(message);
                returnMessage.setContent(user);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
            case "GetUsers":

                break;
            case "AddUser":
                user = userContainerLogic.AddUser(user, token);
                session.getRemote().sendString(gson.toJson(user));
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
