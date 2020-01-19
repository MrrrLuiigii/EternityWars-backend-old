package com.eternitywars.Logic.WebsocketServer.MessageHandler;

import com.eternitywars.Logic.WebsocketServer.Executors.*;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler
{
    private ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void handleMessage(Session session, JSONObject message)
    {
        switch (message.getString("Subject"))
        {
            case "USER":
                executorService.submit(new UserExecutor(message, session));
                break;
            case "LOBBY":
                executorService.submit(new LobbyExecutor(message, session));
                break;
            case "SHOP":
                executorService.submit(new ShopExecutor(message, session));
                break;
            case "GAME":
                executorService.submit(new GameExecutor(message, session));
                break;
            case "FRIEND":
                executorService.submit(new FriendExecutor(message, session));
                break;
            case "DECK":
                executorService.submit(new DeckBuilderExecutor(message, session));
                break;
            case "CARD":
                executorService.submit(new CardExecutor(message, session));
                break;
            case "COLLECTION":
                executorService.submit(new CollectionExecutor(message, session));
                break;
            case "REGISTER":
                executorService.submit(new RegisterExecutor(message, session));
                break;
            case "CHAT":
                executorService.submit(new ChatExecutor(message, session));
                break;
        }
    }
}
