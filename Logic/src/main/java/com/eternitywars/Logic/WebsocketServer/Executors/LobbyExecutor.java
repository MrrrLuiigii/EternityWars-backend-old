package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Lobby.LobbyContainerLogic;
import com.eternitywars.Logic.Lobby.LobbyLogic;

import com.eternitywars.Models.Account;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;



public class LobbyExecutor implements IExecutor  {

    private LobbyLogic lobbyLogic = new LobbyLogic();
    private LobbyContainerLogic lobbyContainerLogic = new LobbyContainerLogic();

    private JSONObject message;
    private Session session;

    public LobbyExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void Execute(JSONObject message, Session session) {
        switch (message.getString("Action")) {
            case "JOINLOBBY":
                Gson gson = new Gson();
                String json = message.getJSONObject("Content").toString();
                Account account = gson.fromJson(json, Account.class);
                System.out.println(account);
                break;
            case "LEAVELOBBY":
                break;
            case "READY":
                break;
            case "ADDLOBBY":
                break;
            case "GETLOBBYBYID":
                break;
            case "GETLOBBIES":
                break;
            case "DELETELOBBY":
                break;
        }
    }

    @Override
    public void run() {
        Execute(message, session);
    }
}
