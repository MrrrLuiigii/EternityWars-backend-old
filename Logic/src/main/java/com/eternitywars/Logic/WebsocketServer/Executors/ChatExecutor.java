package com.eternitywars.Logic.WebsocketServer.Executors;

import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;

public class ChatExecutor implements IExecutor{

    private JSONObject message;
    private Session session;

    public ChatExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void Execute(JSONObject message, Session session) throws IOException {

    }

    @Override
    public void run() {

    }
}
