package com.eternitywars.Logic.WebsocketServer;

import org.eclipse.jetty.websocket.api.Session;

import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class GameWebSocket {
    @OnWebSocketConnect
    public void onConnect(Session session) {
        ServiceBean.getRandomNameService().addSession(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int _closeCode, String _closeReason) {
        ServiceBean.getRandomNameService().removeSession(session);
    }
}
