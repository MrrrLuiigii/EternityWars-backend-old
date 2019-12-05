package com.eternitywars.api.Resources;


import com.eternitywars.api.DAL.Repositories.Deck.DeckContainerRepository;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deck", method = RequestMethod.GET)
public class DeckContainerResource
{
    DeckContainerRepository deckContainerRepository = new DeckContainerRepository();
    @PostMapping(value = "", consumes = "application/json", produces = "application/json")
    public DeckCollection GetAllDeckIdById(@RequestBody() Deck deck)
    {
        return deckContainerRepository.GetAllDecksByUserId(deck);
    }
}
