package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Lobby.LobbyContainerLogic;
import com.eternitywars.Logic.Lobby.LobbyLogic;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

public class LobbyExecutor implements IExecutor  {

    private LobbyLogic lobbyLogic = new LobbyLogic();
    private LobbyContainerLogic lobbyContainerLogic = new LobbyContainerLogic();

    @Override
    public void Execute(JSONObject message, Session session) {

    }
}
