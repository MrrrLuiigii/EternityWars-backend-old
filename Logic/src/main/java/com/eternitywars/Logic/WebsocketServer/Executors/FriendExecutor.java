package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Friend.FriendContainerLogic;
import com.eternitywars.Logic.Friend.FriendLogic;
import com.eternitywars.Models.FriendCollection;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class FriendExecutor implements IExecutor  {

    private FriendContainerLogic friendContainerLogic = new FriendContainerLogic();
    private FriendLogic friendLogic = new FriendLogic();

    @Override
    public void Execute(JSONObject message, Session session) {

    }
}
