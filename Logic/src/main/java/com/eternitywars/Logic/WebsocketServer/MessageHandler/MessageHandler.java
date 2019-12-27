package com.eternitywars.Logic.WebsocketServer.MessageHandler;

import com.eternitywars.Logic.WebsocketServer.Executors.*;
import com.eternitywars.Logic.WebsocketServer.Models.WsMessage;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MessageHandler {

    private LobbyExecutor lobbyExecutor = new LobbyExecutor();
    private ShopExecutor shopExecutor = new ShopExecutor();
    private GameExecutor gameExecutor = new GameExecutor();
    private FriendExecutor friendExecutor = new FriendExecutor();
    private DeckBuilderExecutor deckBuilderExecutor = new DeckBuilderExecutor();
    private CollectionExecutor collectionExecutor = new CollectionExecutor();
    private RegisterExecutor registerExecutor = new RegisterExecutor();

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void handleMessage(Session session, JSONObject message) {
        switch (message.getString("Subject")) {
            case "USER":
                executorService.submit(new UserExecutor(message, session));
                break;
            case "LOBBY":
                lobbyExecutor.Execute(message, session);
                break;
            case "SHOP":
                shopExecutor.Execute(message, session);
                break;
            case "GAME":
                gameExecutor.Execute(message, session);
                break;
            case "FRIEND":
                friendExecutor.Execute(message, session);
                break;
            case "DECK":
                deckBuilderExecutor.Execute(message, session);
                break;
            case "COLLECTION":
                collectionExecutor.Execute(message, session);
                break;
            case "REGISTER":
                registerExecutor.Execute(message, session);
                break;
        }
    }
}
