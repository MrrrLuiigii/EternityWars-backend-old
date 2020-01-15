package com.eternitywars.Logic.Lobby;

import com.eternitywars.Models.Enums.LobbyPlayerStatus;
import com.eternitywars.Models.Lobby;
import com.eternitywars.Models.LobbyCollection;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class LobbyContainerLogic
{
    private RestTemplate restTemplate = new RestTemplate();



    public Lobby AddLobby(Lobby lobby, String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        lobby.getPlayerOne().setLobbyPlayerStatus(LobbyPlayerStatus.NotReady);
        JSONObject json = new JSONObject(lobby);
        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
        //send lobby object with the user that wants to join
        return restTemplate.postForObject("http://localhost:8083/api/private/lobby/add", request, Lobby.class);
    }

    public Lobby GetLobbyById(Lobby lobby, String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject json = new JSONObject(lobby);
        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
        ResponseEntity<Lobby> response = restTemplate.exchange("http://localhost:8083/api/private/lobby/getById/{lobbyId}", HttpMethod.GET, request, Lobby.class, lobby.getId());
        return response.getBody();
    }

    public LobbyCollection GetLobbies(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<LobbyCollection> response = restTemplate.exchange("http://localhost:8083/api/private/lobby/get", HttpMethod.GET, request, LobbyCollection.class);
        return response.getBody();
    }

    public LobbyCollection DeleteLobby(Lobby lobby, String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject json = new JSONObject(lobby);
        HttpEntity<String> request = new HttpEntity<>(json.toString(), headers);
        restTemplate.postForObject("http://localhost:8083/api/private/lobby/delete", request, boolean.class);
        return  GetLobbies(token);
    }
}
