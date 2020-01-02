package com.eternitywars.api.Resources.Lobby;

import com.eternitywars.api.DAL.Repositories.Lobby.LobbyContainerRepository;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;
import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/lobby")
public class LobbyContainerResource
{
    private LobbyContainerRepository lobbyContainerRepository = new LobbyContainerRepository();

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Lobby AddLobby(@RequestBody Lobby lobby)
    {
        return lobbyContainerRepository.AddLobby(lobby);
    }

    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public boolean DeleteLobby(@RequestBody Lobby lobby)
    {
        return lobbyContainerRepository.DeleteLobby(lobby);
    }

    @GetMapping(value = "/getById/{lobbyId}")
    public Lobby GetLobbyById(@PathVariable("lobbyId")int lobbyId)
    {
        return lobbyContainerRepository.GetLobbyById(lobbyId);
    }

    @GetMapping(value = "/get")
    public LobbyCollection GetLobbies()
    {
        return lobbyContainerRepository.GetLobbies();
    }
}
