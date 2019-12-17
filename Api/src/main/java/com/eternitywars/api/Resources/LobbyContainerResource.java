package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.Lobby.LobbyContainerRepository;
import com.eternitywars.api.Models.Lobby;
import com.eternitywars.api.Models.LobbyCollection;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lobby", method = RequestMethod.GET)
public class LobbyContainerResource
{
    LobbyContainerRepository lobbyContainerRepository = new LobbyContainerRepository();

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public LobbyCollection GetLobbies()
    {
        return lobbyContainerRepository.GetLobbies();
    }
}
