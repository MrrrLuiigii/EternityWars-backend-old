package com.eternitywars.Logic.Lobby;

import com.eternitywars.Logic.DeckBuilder.DeckBuilderContainerLogic;
import com.eternitywars.Logic.Game.GameLogic;
import com.eternitywars.Logic.WebsocketServer.Collection.GameCollection;
import com.eternitywars.Models.*;
import com.eternitywars.Models.Enums.LobbyPlayerStatus;
import com.eternitywars.Models.Enums.LobbyStatus;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LobbyLogic
{
    private RestTemplate restTemplate = new RestTemplate();
    private LobbyContainerLogic lobbyContainerLogic = new LobbyContainerLogic();
    private GameLogic gameLogic = new GameLogic();

    public Lobby JoinLobby(Lobby lobby, Player player, String token)
    {
        Lobby lobby1 = lobbyContainerLogic.GetLobbyById(lobby, token);

       if(lobby1.getPlayerOne() != null && lobby1.getPlayerTwo() == null)
       {
           lobby1.setStatus(LobbyStatus.Full);
           HttpHeaders headers = new HttpHeaders();
           headers.setBearerAuth(token);
           headers.setContentType(MediaType.APPLICATION_JSON);
//           Lobby sendlobby = new Lobby();
//           sendlobby.setId(lobby.getId());
//           player.setLobbyPlayerStatus(LobbyPlayerStatus.NotReady);
//           sendlobby.setPlayerOne(player);
           lobby.setPlayerTwo(player);

           JSONObject json = new JSONObject(lobby);

           HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
           //send lobby object with the user that wants to join
           if(restTemplate.postForObject("http://localhost:8083/api/private/lobby/join", request , boolean.class))
           {
               lobby.getPlayerTwo().setLobbyPlayerStatus(LobbyPlayerStatus.NotReady);
               lobby.setStatus(LobbyStatus.Full);
           }
       }
        return lobby;
    }

    public Lobby LeaveLobby(Lobby lobby, Player player, String token)
    {
        if(lobby.getPlayerTwo() != null)
        {
            if(lobby.getPlayerTwo().getUserId() != 0)
            {
                HttpHeaders headers = new HttpHeaders();
                headers.setBearerAuth(token);
                headers.setContentType(MediaType.APPLICATION_JSON);
                Lobby sendlobby = new Lobby();
                sendlobby.setId(lobby.getId());
                lobby.setPlayerOne(player);

                JSONObject json = new JSONObject(lobby);

                HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
                if(restTemplate.postForObject("http://localhost:8083/api/private/lobby/leave", request , boolean.class))
                {
                    lobby.setPlayerTwo(null);
                    return lobby;
                }
            }
        }
        lobbyContainerLogic.DeleteLobby(lobby, token);
        return null;
    }

    public Lobby PlayerReady(Lobby lobby, Player player, String token)
    {
       if(lobby.getPlayerOne().getUserId() == player.getUserId())
       {
           lobby.getPlayerOne().setLobbyPlayerStatus(LobbyPlayerStatus.Ready);
       }
       else if(lobby.getPlayerTwo().getUserId() == player.getUserId())
       {
           lobby.getPlayerTwo().setLobbyPlayerStatus(LobbyPlayerStatus.Ready);
       }
       if(lobby.getPlayerOne() != null && lobby.getPlayerTwo() != null)
       {
           if(lobby.getPlayerOne().getLobbyPlayerStatus() == LobbyPlayerStatus.Ready && lobby.getPlayerTwo().getLobbyPlayerStatus() == LobbyPlayerStatus.Ready)
           {
               DeckBuilderContainerLogic deckBuilderContainerLogic = new DeckBuilderContainerLogic();
               lobby.getPlayerOne().setDeck(deckBuilderContainerLogic.GetDeckById(lobby.getPlayerOne().getDeck().getDeckId(), token));
               lobby.getPlayerTwo().setDeck(deckBuilderContainerLogic.GetDeckById(lobby.getPlayerTwo().getDeck().getDeckId(), token));
               gameLogic.LaunchGame(lobby);
               lobbyContainerLogic.DeleteLobby(lobby, token);

           }
       }
        return lobby;
    }

    public Lobby PlayerNotReady(Lobby lobby, Player player)
    {
        if(lobby.getPlayerOne().getUserId() == player.getUserId())
        {
            lobby.getPlayerOne().setLobbyPlayerStatus(LobbyPlayerStatus.NotReady);
        }
        else if(lobby.getPlayerTwo().getUserId() == player.getUserId())
        {
            lobby.getPlayerTwo().setLobbyPlayerStatus(LobbyPlayerStatus.NotReady);
        }
        return lobby;
    }



    public Lobby SetDeck(Lobby lobby, Player player, String token)
    {
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBearerAuth(token);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        Lobby sendlobby = new Lobby();
//        sendlobby.setId(lobby.getId());
//        sendlobby.setPlayerOne(player);
//
//        JSONObject json = new JSONObject(sendlobby);
//
//        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
//        Lobby lobby1 = restTemplate.postForObject("http://localhost:8083/api/private/lobby/updateDeck", request , Lobby.class);
        if(lobby.getPlayerOne().getUserId() == player.getUserId())
        {
            lobby.getPlayerOne().setDeck(player.getDeck());
        }
        else if(lobby.getPlayerTwo().getUserId() == player.getUserId())
        {
            lobby.getPlayerTwo().setDeck(player.getDeck());
        }
        return  lobby;
    }
}
