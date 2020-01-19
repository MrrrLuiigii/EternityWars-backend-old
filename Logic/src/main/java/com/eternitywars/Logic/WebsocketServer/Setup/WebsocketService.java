package com.eternitywars.Logic.WebsocketServer.Setup;

import com.eternitywars.Logic.WebsocketServer.MessageHandler.MessageHandler;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Service("WebsocketService")
public class WebsocketService {
    private MessageHandler messageHandler = new MessageHandler();
    private Set<Session> listenerSessions = new CopyOnWriteArraySet<>();

    public void removeSession(Session session){
        listenerSessions.remove(session);
    }

    public void addSession(Session session){
        listenerSessions.add(session);
    }

    public void sendmessage(Session session, String message){
        JSONObject jsonObject = new JSONObject(message);
        messageHandler.handleMessage(session,  jsonObject);
    }

    @PostConstruct
    private void init(){
        Runnable runnable = () -> {
            while (true){

            }
        };

        Thread thread = new Thread(runnable);
        thread.start();
    }

}
