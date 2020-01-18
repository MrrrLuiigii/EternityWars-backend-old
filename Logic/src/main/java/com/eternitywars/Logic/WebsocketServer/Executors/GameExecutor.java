package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.ObjectConverter;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.google.gson.Gson;
import com.eternitywars.Logic.Game.GameContainerLogic;
import com.eternitywars.Logic.Game.GameLogic;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import com.eternitywars.Models.*;

import java.io.IOException;

public class GameExecutor implements IExecutor{

    private GameLogic gameLogic = new GameLogic();
    private GameContainerLogic gameContainerLogic = new GameContainerLogic();

    private JSONObject message;
    private Session session;

    @Override
    public void Execute(JSONObject message, Session session) throws IOException {

        WsReturnMessage returnMessage = new WsReturnMessage();
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();
        Game game = ObjectConverter.JSONtoGame(message);
        switch (message.getString("Action")) {
            case "ENDTURN":
                gameLogic.EndTurn(game);
                gameLogic.SendGame(game, "UPDATEGAME");
                break;
            case "ATTACKWITHCARD": //here the logic will get both cards and subtract damage from them and return the game state with the cards that survived. If the Hero is attacked the logic will let the client now.
                gameLogic.AttackCard(game, message.getInt("CardToAttackWith"), message.getInt("TargetToAttack"));
                gameLogic.SendGame(game, "UPDATEGAME");
                break;
            case "PLACECARD": //here the logic will subtract mana from the player and place the card that needs to be placed on the board.

                gameLogic.PlayCard(game, message.getInt("CardToPlay"), message.getInt("SpotToPlace"));
                gameLogic.SendGame(game, "UPDATEGAME");
                break;
            case "ATTACKHERO": //here the logic will cycle through the players turn.

                //Hero hero = (Hero) MessageConverter.FromGsonToObject(new Hero(), message.getJSONObject("Hero").toString());
                gameLogic.AttackHero(game, message.getInt("CardToAttackHeroWith"), message.getString("Token"));
                gameLogic.SendGame(game, "UPDATEGAME");
                break;
//            case "INCREASEMAXMANA":
//                break;
//            case "INCREASEDEATHESSENCE":
//                break;
//            case "RECHARGEMANA":
//                break;
//            case "OBTAINDEATHESSENCE":
//                break;
//            case "STARTTURN": // here the logic will start a thread that keeps track of the time elapsing and will give the client information about that.
//                break;
        }
    }

    public GameExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void run() {
        try {
            Execute(message, session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
