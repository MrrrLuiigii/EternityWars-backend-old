package com.eternitywars.Logic.WebsocketServer.Models;

import com.eternitywars.Logic.WebsocketServer.Models.Enums.Actions.WsUserActionType;
import com.eternitywars.Logic.WebsocketServer.Models.Enums.WsSubjectType;
import org.eclipse.jetty.websocket.api.Session;

public class WsMessage {

    String content;
    Session session;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }


    public WsMessage(){

    }

    public WsMessage(String content, Session session) {

        this.content = content;
        this.session = session;
    }
}
