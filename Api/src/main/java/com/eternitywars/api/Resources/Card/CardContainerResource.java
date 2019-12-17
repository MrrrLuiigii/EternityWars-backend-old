package com.eternitywars.api.Resources.Card;

import com.eternitywars.api.DAL.Repositories.Card.CardContainerRepository;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/card")
public class CardContainerResource
{
    CardContainerRepository cardContainerRepository = new CardContainerRepository();

    @GetMapping(value = "/getByUser/{userId}")
    public CardCollection GetCardsByUser(@PathVariable("userId")int userId)
    {
        User user = new User(userId);
        return cardContainerRepository.GetCardsByUser(user);
    }

    @GetMapping(value = "/get/{cardId}")
    public Card GetCardsById(@PathVariable("cardId")int cardId)
    {
        return cardContainerRepository.GetCardById(cardId);
    }

    @GetMapping(value = "/get")
    public CardCollection GetCards()
    {
        return cardContainerRepository.GetCards();
    }
}
