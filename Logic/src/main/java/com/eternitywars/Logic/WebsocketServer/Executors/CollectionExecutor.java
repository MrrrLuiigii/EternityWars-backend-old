package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Collection.CollectionLogic;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class CollectionExecutor implements IExecutor  {

    private CollectionLogic collectionLogic = new CollectionLogic();

    @Override
    public void Execute(JSONObject message, Session session) {

    }
}
