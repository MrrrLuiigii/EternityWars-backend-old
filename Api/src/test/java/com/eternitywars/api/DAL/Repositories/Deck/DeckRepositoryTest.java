package com.eternitywars.api.DAL.Repositories.Deck;

import com.eternitywars.api.DAL.Repositories.Friend.RelationshipContainerRepository;
import com.eternitywars.api.Factories.Deck.DeckFactory;
import com.eternitywars.api.Factories.Friend.RelationshipContainerFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckRepositoryTest {

    private DeckRepository deckRepository = new DeckRepository(new DeckFactory());


    @Test
    void addCard() {
    }

    @Test
    void deleteCard() {
    }

    @Test
    void updateDeckName() {
    }
}