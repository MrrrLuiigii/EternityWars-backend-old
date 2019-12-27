package com.eternitywars.Logic.WebsocketServer.Executors;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public interface IExecutor extends Runnable {
    void Execute(JSONObject message, Session session);
}
