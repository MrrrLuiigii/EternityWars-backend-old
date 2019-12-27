package com.eternitywars.api.DAL.Repositories.Card;

import com.eternitywars.api.DAL.Contexts.Card.CardContainerSqlContext;
import com.eternitywars.api.Interfaces.Card.ICardContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.User;

public class CardContainerRepository implements ICardContainerContext
{
    private ICardContainerContext cardContainerContext;

    public CardContainerRepository()
    {
        this.cardContainerContext = new CardContainerSqlContext();
    }



    public CardCollection GetCardsByUser(User user)
    {
        return cardContainerContext.GetCardsByUser(user);
    }

    public Card GetCardById(int cardId)
    {
        return cardContainerContext.GetCardById(cardId);
    }

    public CardCollection GetCards()
    {
        return cardContainerContext.GetCards();
    }
}
