package com.eternitywars.api.Resources.Deck;


import com.eternitywars.api.DAL.Repositories.Deck.DeckContainerRepository;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/deck", method = RequestMethod.GET)
public class DeckContainerResource
{
    private DeckContainerRepository deckContainerRepository = new DeckContainerRepository();


    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Deck AddDeck(@RequestBody Deck deck)
    {
        return deckContainerRepository.AddDeck(deck);
    }


    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public DeckCollection GetAllDeckIdById(@PathVariable("userId") int userId)
    {
        return deckContainerRepository.GetAllDecksByUserId(userId);
    }
}
