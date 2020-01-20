package com.eternitywars.api.DAL.Repositories.Card;

import com.eternitywars.api.DAL.Contexts.Card.CardContainerSqlContext;
import com.eternitywars.api.Factories.Card.CardContainerFactory;
import com.eternitywars.api.Factories.User.UserContainerFactory;
import com.eternitywars.api.Interfaces.Card.ICardContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.User;

public class CardContainerRepository implements ICardContainerContext
{
    private ICardContainerContext cardContainerContext;

    public CardContainerRepository() {
        CardContainerFactory cardContainerFactory = new CardContainerFactory();
        this.cardContainerContext = cardContainerFactory.getCardContainerSqlContext();
    }

    public CardContainerRepository(CardContainerFactory cardContainerFactory) {
        this.cardContainerContext = cardContainerFactory.getTestCardContainerSqlContext();
    }

    public CardCollection GetCards()
    {
        return cardContainerContext.GetCards();
    }

    public CardCollection GetCardsByUser(int userId)
    {
        return cardContainerContext.GetCardsByUser(userId);
    }

    public Card GetCardById(int cardId)
    {
        return cardContainerContext.GetCardById(cardId);
    }

    public boolean AddCard(User user, Card card)
    {
        return cardContainerContext.AddCard(user, card);
    }

    public boolean DeleteCard(User user, Card card)
    {
        return cardContainerContext.DeleteCard(user, card);
    }
}
