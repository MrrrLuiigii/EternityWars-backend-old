package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Models.Account;
import com.google.gson.Gson;
import com.eternitywars.Logic.Game.GameContainerLogic;
import com.eternitywars.Logic.Game.GameLogic;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class GameExecutor implements IExecutor{

    private GameLogic gameLogic = new GameLogic();
    private GameContainerLogic gameContainerLogic = new GameContainerLogic();

    private JSONObject message;
    private Session session;

    @Override
    public void Execute(JSONObject message, Session session) {
        switch (message.getString("Action")) {
            case "STARTGAME":// here the logic will determine which player is going to start and will return the mana, death essence and the cards the players will have at the start of the game
                Gson gson = new Gson();
                String json = message.getJSONObject("Content").toString();
                Account account = gson.fromJson(json, Account.class);
                System.out.println(account);
                break;
            case "RECONNECT":
                break;
            case "LEAVEGAME":
                break;
            case "ADDGAME":
                break;
            case "GETGAMEBYID":
                break;
            case "DELETEGAME":
                break;
            case "STARTTURN": // here the logic will start a thread that keeps track of the time elapsing and will give the client information about that.
                break;
            case "ATTACKWITHCARD": //here the logic will get both cards and subtract damage from them and return the game state with the cards that survived. If the Hero is attacked the logic will let the client now.
                break;
            case "PLACECARD": //here the logic will subtract mana from the player and place the card that needs to be placed on the board.
                break;
            case "SKIPTURN": //here the logic will cycle through the players turn.
                break;
        }
    }

    public GameExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void run() {
        Execute(message, session);
    }
}
