package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Friend.FriendContainerLogic;
import com.eternitywars.Logic.Friend.FriendLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.FriendCollection;
import com.eternitywars.Models.User;
import com.eternitywars.Models.UserCollection;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;

public class FriendExecutor implements IExecutor
{
    private FriendLogic friendLogic = new FriendLogic();
    private FriendContainerLogic friendContainerLogic = new FriendContainerLogic();

    private UserCollection userCollection = new UserCollection();

    private JSONObject message;
    private Session session;



    @Override
    public void Execute(JSONObject jsonObject, Session session) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        switch (jsonObject.getString("Action"))
        {
            case "INVITE":
                friendContainerLogic.InviteFriend(jsonObject);
                RespondFriendCollection(jsonObject);
                break;
            case "ACCEPTREQUEST":
                friendLogic.AcceptFriend(jsonObject);
                RespondFriendCollection(jsonObject);
                break;
            case "BLOCKREQUEST":
                friendLogic.BlockFriend(jsonObject);
                RespondFriendCollection(jsonObject);
                break;
            case "REMOVEFRIEND":
                friendContainerLogic.RemoveFriend(jsonObject);
                RespondFriendCollection(jsonObject);
                break;
            default:
                User user = gson.fromJson(jsonObject.getJSONObject("Content").toString(), User.class);
                String token = jsonObject.getString("Token");
                FriendCollection friendCollection = friendContainerLogic.GetAllFriends(user, token);

                WsReturnMessage returnMessage = new WsReturnMessage();
                returnMessage.setAction("GETALLFRIENDS");
                returnMessage.setContent(friendCollection);
                session.getRemote().sendString(gson.toJson(returnMessage));
                break;
        }
    }

    private void RespondFriendCollection(JSONObject jsonObject) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        //Get the user object from the jsonObject
        User user = gson.fromJson(jsonObject.getJSONObject("Content").toString(), User.class);

        String token = jsonObject.getString("Token");

        String friendName = "";

        try
        {
            friendName = jsonObject.getString("friendname");
        }
        catch (Exception e)
        {

        }

        //Get friendCollection from API via friendContainerLogic
        FriendCollection friendCollection = friendContainerLogic.GetAllFriends(user, token);

        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction("GETALLFRIENDS");
        returnMessage.setContent(friendCollection);
        session.getRemote().sendString(gson.toJson(returnMessage));

        for(User u : userCollection.getUsers())
        {
            if (u.getUsername().equals(friendName))
            {
                u.getSession().getRemote().sendString(gson.toJson(returnMessage));
            }
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
