package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.Lobby.LobbyContainerRepository;
import com.eternitywars.api.Models.LobbyDataCollection;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/lobby", method = RequestMethod.GET)
public class LobbyContainerResource
{
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public LobbyDataCollection GetLobbies(){
        LobbyContainerRepository repository = new LobbyContainerRepository();
        return repository.GetLobbies();
    }
}
