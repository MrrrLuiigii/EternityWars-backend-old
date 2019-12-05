package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.Card.CardContainerRepository;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/card", method = RequestMethod.GET)
public class CardContainerResource
{
    @RequestMapping(value = "/get/userid/{userId}", method = RequestMethod.GET)
    public CardCollection GetCardsByUserId(@PathVariable("userId")int userId)
    {
        CardContainerRepository cardContainerRepository = new CardContainerRepository();
        return cardContainerRepository.GetCardsByUserId(userId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
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
