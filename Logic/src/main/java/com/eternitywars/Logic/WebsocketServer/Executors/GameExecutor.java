package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Game.GameContainerLogic;
import com.eternitywars.Logic.Game.GameLogic;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class GameExecutor implements IExecutor  {

    private GameLogic gameLogic = new GameLogic();
    private GameContainerLogic gameContainerLogic = new GameContainerLogic();

    @Override
    public void Execute(JSONObject message, Session session) {

    }
}
