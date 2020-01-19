package com.eternitywars.api.Resources.Deck;

import com.eternitywars.api.DAL.Repositories.Deck.DeckRepository;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.Deck;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/private/deck")
public class DeckResource
{
    private DeckRepository deckRepository = new DeckRepository();



    @PostMapping(value = "/addCard", consumes = "application/json", produces = "application/json")
    public boolean AddCard(@RequestBody Deck deck)
    {
        Card card = deck.getCards().getCards().get(0);
        return deckRepository.AddCard(deck, card);
    }

    @PostMapping(value = "/deleteCard", consumes = "application/json", produces = "application/json")
    public boolean DeleteCard(@RequestBody Deck deck)
    {
        Card card = deck.getCards().getCards().get(0);
        return deckRepository.DeleteCard(deck, card);
    }

    @PostMapping(value = "/updateName", consumes = "application/json", produces = "application/json")
    public boolean UpdateDeckName(@RequestBody Deck deck)
    {
        return deckRepository.UpdateDeckName(deck);
    }
}
