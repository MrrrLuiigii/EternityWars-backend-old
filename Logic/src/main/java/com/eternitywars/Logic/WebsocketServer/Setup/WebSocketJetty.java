package com.eternitywars.Logic.WebsocketServer.Setup;

import org.eclipse.jetty.websocket.api.Session;

import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class WebSocketJetty {
    @OnWebSocketConnect
    public void onConnect(Session session) {
        System.out.println("Nigga im in");
        ServiceBean.getService().addSession(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int _closeCode, String _closeReason) {
        ServiceBean.getService().removeSession(session);
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String text){
        ServiceBean.getService().sendmessage(session, text);
    }
}
