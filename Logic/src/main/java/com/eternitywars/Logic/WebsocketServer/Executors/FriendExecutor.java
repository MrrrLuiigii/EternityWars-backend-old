package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Friend.FriendContainerLogic;
import com.eternitywars.Logic.Friend.FriendLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.FriendCollection;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;

public class FriendExecutor implements IExecutor
{
    private FriendContainerLogic friendContainerLogic = new FriendContainerLogic();
    private FriendLogic friendLogic = new FriendLogic();

    private JSONObject message;
    private Session session;


    @Override
    public void Execute(JSONObject jsonObject, Session session) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();
        User user;
        String token;
        WsReturnMessage returnMessage = new WsReturnMessage();
        FriendCollection content;
        switch (jsonObject.getString("Action"))
        {
            case "INVITE":
                friendLogic.InviteFriend(jsonObject);
                JSONObject userContent = jsonObject.getJSONObject("Content");
                user = gson.fromJson(userContent.getJSONObject("user").toString(), User.class);
                token = jsonObject.getString("Token");
                content = friendContainerLogic.GetAllFriends(user, token);
                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(content);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
            case "ACCEPTREQUEST":
                friendLogic.AcceptFriend(jsonObject);
                JSONObject userContent2 = jsonObject.getJSONObject("Content");
                user = gson.fromJson(userContent2.getJSONObject("user").toString(), User.class);
                token = jsonObject.getString("Token");
                content = friendContainerLogic.GetAllFriends(user, token);
                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(content);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
            case "BLOCKREQUEST":
                friendLogic.BlockFriend(jsonObject);
                JSONObject userContent1 = jsonObject.getJSONObject("Content");
                user = gson.fromJson(userContent1.getJSONObject("user").toString(), User.class);
                token = jsonObject.getString("Token");
                content = friendContainerLogic.GetAllFriends(user, token);
                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(content);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
            case "REMOVEFRIEND":
                friendLogic.RemoveFriend(jsonObject);
                JSONObject userContent4 = jsonObject.getJSONObject("Content");
                user = gson.fromJson(userContent4.getJSONObject("user").toString(), User.class);
                token = jsonObject.getString("Token");
                content = friendContainerLogic.GetAllFriends(user, token);
                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(content);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
            default:
                user = gson.fromJson(jsonObject.getJSONObject("Content").toString(), User.class);
                token = jsonObject.getString("Token");
                content = friendContainerLogic.GetAllFriends(user, token);
                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(content);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
        }


    }

    public FriendExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
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
