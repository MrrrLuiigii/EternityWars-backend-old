package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Lobby.LobbyContainerLogic;
import com.eternitywars.Logic.Lobby.LobbyLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import java.io.IOException;

public class LobbyExecutor implements IExecutor
{
    private LobbyLogic lobbyLogic = new LobbyLogic();
    private LobbyContainerLogic lobbyContainerLogic = new LobbyContainerLogic();

    private JSONObject message;
    private Session session;
    private String lobbyJson;
    private Lobby lobbyObject;
    private Lobby lobby;
    private LobbyCollection lobbyCollection;
    private String playerJson;
    private Player player;

    public LobbyExecutor(JSONObject message, Session session)
    {
        this.message = message;
        this.session = session;
    }

    @Override
    public void Execute(JSONObject message, Session session) throws IOException
    {
        Gson gson = new Gson();

        try
        {
            lobbyJson = message.getJSONObject("Lobby").toString();
            lobbyObject = gson.fromJson(lobbyJson, Lobby.class);
        }catch(Exception e)
        {
            System.out.println(e);
        }

        try
        {
            playerJson = message.getJSONObject("Player").toString();
            player = gson.fromJson(playerJson, Player.class);
        }catch(Exception e)
        {
            System.out.println(e);
        }

        String token = message.getString("Token");

        switch (message.getString("Action"))
        {
            case "JOINLOBBY":
                lobby = lobbyLogic.JoinLobby(lobbyObject, player,  token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "LEAVELOBBY":
                lobby = lobbyLogic.LeaveLobby(lobbyObject, player,  token);
                RespondLobbyCollection(message);
                break;
            case "PLAYERREADY":
                lobby = lobbyLogic.PlayerReady(lobbyObject, player);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "PLAYERNOTREADY":
                lobby = lobbyLogic.PlayerNotReady(lobbyObject, player);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "SETDECK":
                lobby = lobbyLogic.SetDeck(lobbyObject, player, token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "ADDLOBBY":
                lobby = lobbyContainerLogic.AddLobby(lobbyObject, token);
                RespondLobbyCollection(message);
                break;
            case "GETLOBBYBYID":
                lobby = lobbyContainerLogic.GetLobbyById(lobbyObject, token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "GETLOBBIES":
                lobbyCollection = lobbyContainerLogic.GetLobbies(token);
                RespondLobbyCollection(message);
                break;
            case "DELETELOBBY":
                LobbyCollection lobbyCollection = lobbyContainerLogic.DeleteLobby(lobbyObject, token);
                RespondLobbyCollection(message);
                break;
        }
    }

    private void RespondLobbyCollection(JSONObject jsonObject) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        //Get the user object from the jsonObject
        JSONObject userJsonObject = jsonObject.getJSONObject("Content");

        String token = jsonObject.getString("Token");

        //Get friendCollection from API via friendContainerLogic
        lobbyCollection = lobbyContainerLogic.GetLobbies(token);

        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction("GETALLLOBBIES");
        returnMessage.setContent(lobbyCollection);
        session.getRemote().sendString(gson.toJson(returnMessage));
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
