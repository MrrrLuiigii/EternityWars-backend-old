package com.eternitywars.api.Resources.Deck;


import com.eternitywars.api.DAL.Repositories.Deck.DeckContainerRepository;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/deck")
public class DeckContainerResource
{
    private DeckContainerRepository deckContainerRepository = new DeckContainerRepository();



    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public Deck AddDeck(@RequestBody Deck deck)
    {
        return deckContainerRepository.AddDeck(deck);
    }

    @PostMapping(value = "/delete", consumes = "application/json", produces = "application/json")
    public boolean DeleteDeck(@RequestBody Deck deck)
    {
        return deckContainerRepository.DeleteDeck(deck);
    }

    @GetMapping(value = "/getEmptyByUserId/{userId}")
    public DeckCollection GetEmptyDecksByUserId(@PathVariable("userId") int userId)
    {
        return deckContainerRepository.GetEmptyDecksByUserId(userId);
    }

    @GetMapping(value = "/getEmptyByDeckId/{deckId}")
    public Deck GetEmptyDeckById(@PathVariable("deckId") int deckId)
    {
        return deckContainerRepository.GetEmptyDeckById(deckId);
    }

    @GetMapping(value = "/getByUserId/{userId}")
    public DeckCollection GetDecksByUserId(@PathVariable("userId") int userId)
    {
        return deckContainerRepository.GetDecksByUserId(userId);
    }

    @GetMapping(value = "/getByDeckId/{deckId}")
    public Deck GetDeckById(@PathVariable("deckId") int deckId)
    {
        return deckContainerRepository.GetDeckById(deckId);
    }
}
