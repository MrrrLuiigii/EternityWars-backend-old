package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.CardContainerContext;
import com.eternitywars.api.Interfaces.ICardContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;

public class CardContainerRepository implements ICardContainerContext
{
    public CardCollection GetCards()
    {
        CardContainerContext cardContainerContext = new CardContainerContext();
        return cardContainerContext.GetCards();
    }

    public CardCollection GetCardsByUserId(int userId)
    {
        CardContainerContext cardContainerContext = new CardContainerContext();
        return cardContainerContext.GetCardsByUserId(userId);
    }

    public Card GetCardById(int cardId)
    {
        CardContainerContext cardContainerContext = new CardContainerContext();
        return cardContainerContext.GetCardById(cardId);
    }
}
