package com.eternitywars.api.Interfaces.Card;

import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.User;

public interface ICardContainerContext
{
    CardCollection GetCards();

    CardCollection GetCardsByUser(User user);

    Card GetCardById(int cardId);

    void AddCardToAccount();
}
