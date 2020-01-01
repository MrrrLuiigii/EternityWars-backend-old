package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Lobby.LobbyContainerLogic;
import com.eternitywars.Logic.Lobby.LobbyLogic;

import com.eternitywars.Models.Account;
import com.eternitywars.Models.Lobby;
import com.eternitywars.Models.Player;
import com.google.gson.Gson;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;


public class LobbyExecutor implements IExecutor  {

    private LobbyLogic lobbyLogic = new LobbyLogic();
    private LobbyContainerLogic lobbyContainerLogic = new LobbyContainerLogic();

    private JSONObject message;
    private Session session;
    private String lobbyjson;
    private Lobby lobbyobject;
    private Lobby lobby;
    private String playerjson;
    private Player player;

    public LobbyExecutor(JSONObject message, Session session) {
        this.message = message;
        this.session = session;
    }

    @Override
    public void Execute(JSONObject message, Session session) throws IOException {

        Gson gson = new Gson();
        try {
            lobbyjson = message.getJSONObject("Lobby").toString();
            lobbyobject = gson.fromJson(lobbyjson, Lobby.class);
        }catch(Exception e){
            System.out.println(e);
        }
        try{
            playerjson = message.getJSONObject("Player").toString();
            player = gson.fromJson(playerjson ,Player.class);
        }catch(Exception e){
            System.out.println(e);
        }

        String token = message.getString("token");

        switch (message.getString("Action")) {
            case "JOINLOBBY":
                lobby = lobbyLogic.JoinLobby(lobbyobject, player,  token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "LEAVELOBBY":
                lobby = lobbyLogic.LeaveLobby(lobbyobject, player,  token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "PLAYERREADY":
                lobby = lobbyLogic.PlayerReady(lobbyobject, player);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "PLAYERNOTREADY":
                lobby = lobbyLogic.PlayerNotReady(lobbyobject, player);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "SETDECK":
                lobby = lobbyLogic.SetDeck(lobbyobject, player, token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "ADDLOBBY":
                lobby = lobbyContainerLogic.AddLobby(lobbyobject, token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
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
        try {
            Execute(message, session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
