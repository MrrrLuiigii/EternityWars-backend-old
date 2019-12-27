package com.eternitywars.Logic.WebsocketServer.MessageHandler;

import com.eternitywars.Logic.WebsocketServer.Executors.*;
import com.eternitywars.Logic.WebsocketServer.Models.WsMessage;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class MessageHandler {

    private UserExecutor userExecutor = new UserExecutor();
    private LobbyExecutor lobbyExecutor = new LobbyExecutor();
    private ShopExecutor shopExecutor = new ShopExecutor();
    private GameExecutor gameExecutor = new GameExecutor();
    private FriendExecutor friendExecutor = new FriendExecutor();
    private DeckBuilderExecutor deckBuilderExecutor = new DeckBuilderExecutor();
    private CollectionExecutor collectionExecutor = new CollectionExecutor();

    public void handleMessage(Session session, JSONObject message) {
        switch (message.getString("Subject")) {
            case "USER":
                userExecutor.Execute(message, session);
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
        }
    }
}
