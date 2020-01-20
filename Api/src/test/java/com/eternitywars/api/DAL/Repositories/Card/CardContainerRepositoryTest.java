package com.eternitywars.api.DAL.Repositories.Card;

import com.eternitywars.api.Factories.Card.CardContainerFactory;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardContainerRepositoryTest {


    private CardContainerRepository cardContainerRepository = new CardContainerRepository(new CardContainerFactory());

    private Card CardExpectedCard(){
        Card expectedCard = new Card();
        expectedCard.setCardId(1);
        expectedCard.setAttack(1);
        expectedCard.setBlue_mana(1);
        expectedCard.setDeath_essence(1);
        expectedCard.setTaunt(true);
        expectedCard.setName("Fedex");
        expectedCard.setHealth(1);

        return expectedCard;
    }

    private Card CardToAdd(){
        Card expectedCard = new Card();
        expectedCard.setCardId(2);
        expectedCard.setAttack(1);
        expectedCard.setBlue_mana(1);
        expectedCard.setDeath_essence(1);
        expectedCard.setTaunt(true);
        expectedCard.setName("Fronk");
        expectedCard.setHealth(1);

        return expectedCard;
    }


    @Test
    void getCards() {
        CardCollection cardCollection = cardContainerRepository.GetCards();
        assertEquals(2, cardCollection.getCards().size());
    }

    @Test
    void getCardsByUser() {
        CardCollection cardCollection = cardContainerRepository.GetCardsByUser(1);
        assertEquals(2, cardCollection.getCards().size());
    }

    @Test
    void getCardById() {
        Card expected = CardExpectedCard();
        Card card = cardContainerRepository.GetCardById(1);

        assertEquals(expected.getAttack(), card.getAttack());
        assertEquals(expected.getCardId(), card.getCardId());
        assertEquals(expected.getBlue_mana(), card.getBlue_mana());
        assertEquals(expected.getDeath_essence(), card.getDeath_essence());
        assertEquals(expected.getTaunt(), card.getTaunt());

    }

    @Test
    void addCard() {
        User user = new User();
        user.setUserId(1);
        Card cardToAdd = CardToAdd();
        assertEquals(true, cardContainerRepository.AddCard(user, cardToAdd));
    }

    @Test
    void deleteCard() {
        User user = new User();
        user.setUserId(1);
        Card cardToAdd = CardToAdd();
        assertEquals(true, cardContainerRepository.DeleteCard(user, cardToAdd));
    }
}