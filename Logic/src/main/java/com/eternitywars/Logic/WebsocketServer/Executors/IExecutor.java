package com.eternitywars.Logic.WebsocketServer.Executors;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;

public interface IExecutor extends Runnable {
    void Execute(JSONObject message, Session session) throws IOException;
}
