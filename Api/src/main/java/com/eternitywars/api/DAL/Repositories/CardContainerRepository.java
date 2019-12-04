package com.eternitywars.api.DAL.Repositories;

import com.eternitywars.api.DAL.Contexts.CardContainerContext;
import com.eternitywars.api.Interfaces.ICardContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;

public class CardContainerRepository implements ICardContainerContext
{
    private ICardContainerContext cardContainerContext;

    public CardContainerRepository()
    {
        this.cardContainerContext = new CardContainerContext();
    }

    public CardCollection GetCards()
    {
        return cardContainerContext.GetCards();
    }

    public CardCollection GetCardsByUserId(int userId)
    {
        return cardContainerContext.GetCardsByUserId(userId);
    }

    public Card GetCardById(int cardId)
    {
        return cardContainerContext.GetCardById(cardId);
    }
}
