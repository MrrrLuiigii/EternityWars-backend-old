package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.Deck.DeckRepository;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.Deck;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/deck", method = RequestMethod.GET)
public class DeckResource
{
    DeckRepository deckRepository = new DeckRepository();
    @PostMapping(value = "/yeet", consumes = "application/json", produces = "application/json")
    public CardCollection GetCardsInDeck(@RequestBody Deck deck)
    {
        return deckRepository.GetCardsInDeck(deck);
    }




}
