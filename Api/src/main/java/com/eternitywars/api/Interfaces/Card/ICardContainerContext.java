package com.eternitywars.api.Interfaces.Card;

import com.eternitywars.api.Factories.User.UserContainerFactory;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.User;

public interface ICardContainerContext
{
    CardCollection GetCards();

    CardCollection GetCardsByUser(int userId);

    Card GetCardById(int cardId);

    boolean AddCard(User user, Card card);

    boolean DeleteCard(User user, Card card);
}
