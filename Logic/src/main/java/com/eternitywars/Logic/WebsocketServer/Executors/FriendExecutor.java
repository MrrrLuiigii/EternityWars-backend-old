package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Friend.FriendContainerLogic;
import com.eternitywars.Logic.Friend.FriendLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.FriendCollection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;

public class FriendExecutor implements IExecutor
{
    private FriendLogic friendLogic = new FriendLogic();
    private FriendContainerLogic friendContainerLogic = new FriendContainerLogic();

    private JSONObject message;
    private Session session;



    @Override
    public void Execute(JSONObject jsonObject, Session session) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        WsReturnMessage returnMessage = new WsReturnMessage();
        FriendCollection content;

        switch (jsonObject.getString("Action"))
        {
            case "INVITE":
                friendLogic.InviteFriend(jsonObject);
                break;
            case "ACCEPTREQUEST":
                friendLogic.AcceptFriend(jsonObject);
                break;
            case "BLOCKREQUEST":
                friendLogic.BlockFriend(jsonObject);
                break;
            case "REMOVEFRIEND":
                friendLogic.RemoveFriend(jsonObject);
                break;
            default:
                break;
        }

        content = friendContainerLogic.GetAllFriends(jsonObject);
        returnMessage.setAction("GETALLFRIENDS");
        returnMessage.setContent(content);
        session.getRemote().sendString(gson.toJson(returnMessage));
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
