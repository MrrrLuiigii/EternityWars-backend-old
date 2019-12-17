package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.Lobby.LobbyContainerRepository;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/lobby", method = RequestMethod.GET)
public class LobbyContainerResource
{
    LobbyContainerRepository lobbyContainerRepository = new LobbyContainerRepository();

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
