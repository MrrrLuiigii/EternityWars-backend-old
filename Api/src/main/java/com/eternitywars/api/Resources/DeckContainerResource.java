package com.eternitywars.api.Resources;


import com.eternitywars.api.DAL.Repositories.DeckContainerRepository;
import com.eternitywars.api.Models.DeckCollection;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/deck", method = RequestMethod.GET)
public class DeckContainerResource
{
    DeckContainerRepository deckContainerRepository = new DeckContainerRepository();
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public DeckCollection GetAllDeckIdById(@PathVariable("userId") int userId)
    {
        return deckContainerRepository.GetAllDeckById(userId);
    }
}
