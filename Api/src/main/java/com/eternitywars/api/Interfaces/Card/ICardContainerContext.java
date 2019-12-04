package com.eternitywars.api.Interfaces.Card;

import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;

public interface ICardContainerContext
{
    CardCollection GetCards();
    CardCollection GetCardsByUserId(int userId);
    Card GetCardById(int cardId);
}
