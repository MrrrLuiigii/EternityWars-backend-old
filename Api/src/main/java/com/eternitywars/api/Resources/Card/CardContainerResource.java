package com.eternitywars.api.Resources.Card;

import com.eternitywars.api.DAL.Repositories.Card.CardContainerRepository;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "api", method = RequestMethod.GET)
public class CardContainerResource
{
    //TODO refactor this which route is private which is public this is only an example
    @GetMapping(value = "/private/card/get/userid/{userId}")
    public CardCollection GetCardsByUserId(@PathVariable("userId")int userId)
    {
        CardContainerRepository cardContainerRepository = new CardContainerRepository();
        return cardContainerRepository.GetCardsByUserId(userId);
    }

    @GetMapping(value = "/public/card/get")
    public CardCollection GetCards()
    {
        CardContainerRepository cardContainerRepository = new CardContainerRepository();
        return cardContainerRepository.GetCards();
    }

    @RequestMapping(value = "/get/{cardId}", method = RequestMethod.GET)
    public Card GetCardsById(@PathVariable("cardId")int cardId)
    {
        CardContainerRepository cardContainerRepository = new CardContainerRepository();
        return cardContainerRepository.GetCardById(cardId);
    }
}
