package com.eternitywars.Logic.WebsocketServer.Setup;

import com.eternitywars.Logic.WebsocketServer.Collection.UserCollection;
import com.eternitywars.Models.User;
import org.eclipse.jetty.websocket.api.Session;

import org.eclipse.jetty.websocket.api.annotations.*;

@WebSocket
public class WebSocketJetty {
    @OnWebSocketConnect
    public void onConnect(Session session) {
        ServiceBean.getService().addSession(session);
    }

    @OnWebSocketClose
    public void onClose(Session session, int _closeCode, String _closeReason) {
        ServiceBean.getService().removeSession(session);
        UserCollection.getConnectedUsers().remove(UserCollection.getUserBySession(session));
    }

    @OnWebSocketMessage
    public void onMessage(Session session, String text){
        ServiceBean.getService().sendmessage(session, text);
    }

}
