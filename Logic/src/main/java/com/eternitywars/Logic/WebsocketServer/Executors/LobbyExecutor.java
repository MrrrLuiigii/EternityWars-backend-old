package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Lobby.LobbyContainerLogic;
import com.eternitywars.Logic.Lobby.LobbyLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.Lobby;
import com.eternitywars.Models.LobbyCollection;
import com.eternitywars.Models.Player;
import com.google.gson.Gson;
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
        WsReturnMessage returnMessage = new WsReturnMessage();

        try
        {
            lobbyJson = message.getJSONObject("Content").toString();
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
//                lobby = lobbyLogic.JoinLobby(lobbyObject, player,  token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "LEAVELOBBY":
                lobby = lobbyLogic.LeaveLobby(lobbyObject, player,  token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
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
                returnMessage = new WsReturnMessage();
                returnMessage.setContent(lobby);
                returnMessage.setAction("JOINLOBBY");

                session.getRemote().sendString(new JSONObject(returnMessage).toString());
                updateLobbies(session, token);
                break;
            case "GETLOBBYBYID":
                lobby = lobbyContainerLogic.GetLobbyById(lobbyObject, token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "GETLOBBIES":
                lobbyCollection = lobbyContainerLogic.GetLobbies(token);
                returnMessage.setContent(lobbyCollection);
                returnMessage.setAction("GETLOBBIES");

                session.getRemote().sendString(new JSONObject(returnMessage).toString());
                break;
            case "DELETELOBBY":
//                lobbyContainerLogic.DeleteLobby(lobbyObject)
                break;
        }
    }

    private void updateLobbies(Session session, String token) throws IOException {
        lobbyCollection = lobbyContainerLogic.GetLobbies(token);
        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setContent(lobbyCollection);
        returnMessage.setAction("GETLOBBIES");

        session.getRemote().sendString(new JSONObject(returnMessage).toString());
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
