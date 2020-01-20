package com.eternitywars.api.DAL.Repositories.Card;

import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardContainerRepositoryTest {


    @Test
    void getCards() {
        CardContainerRepository cardContainerRepository = new CardContainerRepository();
        CardCollection cardCollection = cardContainerRepository.GetCards();
        assertEquals(48, cardCollection.getCards().size());
    }

    @Test
    void getCardsByUser() {
        CardContainerRepository cardContainerRepository = new CardContainerRepository();
        CardCollection cardCollection = cardContainerRepository.GetCardsByUser(25);

        assertEquals(1, cardCollection.getCards().size());
    }

    @Test
    void getCardById() {
        CardContainerRepository cardContainerRepository = new CardContainerRepository();
        Card card = cardContainerRepository.GetCardById(1);

        assertEquals("Dreadlord von Reemer", card.getName());
    }

    @Test
    void addCard() {
    }

    @Test
    void deleteCard() {
    }
}