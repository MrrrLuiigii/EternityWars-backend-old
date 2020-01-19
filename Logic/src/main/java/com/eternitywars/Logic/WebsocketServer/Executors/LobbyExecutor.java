package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Lobby.LobbyContainerLogic;
import com.eternitywars.Logic.Lobby.LobbyLogic;
import com.eternitywars.Logic.WebsocketServer.Collection.UserCollection;
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
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();
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
                lobby = lobbyLogic.JoinLobby(lobbyObject, player,  token);
                returnMessage = new WsReturnMessage();
                returnMessage.setContent(lobby);
                returnMessage.setAction("JOINLOBBY");
                session.getRemote().sendString(gson.toJson(returnMessage));

                RespondLobby(lobby);
                RespondLobbyCollection(message);
                break;
            case "LEAVELOBBY":
                lobby = lobbyLogic.LeaveLobby(lobbyObject, player,  token);
                RespondLobbyCollection(message);
                RespondLobby(lobby);
                break;
            case "PLAYERREADY":
                lobby = lobbyLogic.PlayerReady(lobbyObject, player, token);
                RespondLobby(lobby);
                break;
            case "PLAYERNOTREADY":
                lobby = lobbyLogic.PlayerNotReady(lobbyObject, player);
                RespondLobby(lobby);
                break;
            case "SETDECK":
                lobby = lobbyLogic.SetDeck(lobbyObject, player, token);
                RespondLobby(lobby);
                break;
            case "ADDLOBBY":
                lobby = lobbyContainerLogic.AddLobby(lobbyObject, token);
                
                returnMessage = new WsReturnMessage();
                returnMessage.setContent(lobby);
                returnMessage.setAction("JOINLOBBY");
                session.getRemote().sendString(gson.toJson(returnMessage));

                RespondLobbyCollection(message);
                break;
            case "GETLOBBYBYID":
                lobby = lobbyContainerLogic.GetLobbyById(lobbyObject, token);
                session.getRemote().sendString(new JSONObject(lobby).toString());
                break;
            case "GETLOBBIES":
                RespondLobbyCollection(message);
                break;
            case "DELETELOBBY":
                LobbyCollection lobbyCollection = lobbyContainerLogic.DeleteLobby(lobbyObject, token);
                RespondLobbyCollection(message);
                break;
        }
    }

    private void RespondLobby(Lobby lobby) throws IOException {

        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();
        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction("UPDATELOBBY");
        returnMessage.setContent(lobby);

        for (User u : UserCollection.getConnectedUsers()){
            if(lobby.getPlayerOne() != null){
                if(u.getUsername().equals(lobby.getPlayerOne().getUsername())){
                    u.getSession().getRemote().sendString(gson.toJson(returnMessage));
                }
            }
            if(lobby.getPlayerTwo() != null){
                if(u.getUsername().equals(lobby.getPlayerTwo().getUsername())){
                    u.getSession().getRemote().sendString(gson.toJson(returnMessage));
                }
            }
        }
    }

    private void RespondLobbyCollection(JSONObject jsonObject) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        String token = jsonObject.getString("Token");
        lobbyCollection = lobbyContainerLogic.GetLobbies(token);

        //update all register sessions
        for (User user : UserCollection.getConnectedUsers())
        {
            WsReturnMessage returnMessage = new WsReturnMessage();
            returnMessage.setAction("GETLOBBIES");
            returnMessage.setContent(lobbyCollection);
            user.getSession().getRemote().sendString(gson.toJson(returnMessage));
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
