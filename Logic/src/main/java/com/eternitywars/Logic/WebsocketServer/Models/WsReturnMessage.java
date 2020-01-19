package com.eternitywars.Logic.WebsocketServer.Models;

public class WsReturnMessage {
    Object content;
    String action;

    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public WsReturnMessage(Object content, String action) {
        this.content = content;
        this.action = action;
    }


    public WsReturnMessage() {
    }
}
