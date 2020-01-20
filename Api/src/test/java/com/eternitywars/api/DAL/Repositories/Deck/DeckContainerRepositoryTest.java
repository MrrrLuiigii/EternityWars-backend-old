package com.eternitywars.api.DAL.Repositories.Deck;

import com.eternitywars.api.Factories.Deck.DeckContainerFactory;
import com.eternitywars.api.Factories.Deck.DeckFactory;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckContainerRepositoryTest {

    private DeckContainerRepository deckContainerRepository = new DeckContainerRepository(new DeckContainerFactory());

    private int deckToDeleteId;

    private DeckCollection SetupGetDeckByUserId() {
        DeckCollection userOneDeckCollection = new DeckCollection();

        Deck userOneDeckOne = new Deck();
        userOneDeckOne.setDeckId(1);
        userOneDeckOne.setUserId(1);
        userOneDeckOne.setName("getByUserIdDeckOne");

        CardCollection deckOneCardCollection = new CardCollection();
        Card card = new Card();
        card.setCardId(2);
        card.setName("Africa");
        card.setAttack(1);
        card.setHealth(1);
        card.setBlue_mana(1);
        card.setDeath_essence(1);
        card.setTaunt(true);
        deckOneCardCollection.AddCard(card);
        userOneDeckOne.setCards(deckOneCardCollection);

        Deck userOneDeckTwo = new Deck();
        userOneDeckTwo.setDeckId(2);
        userOneDeckTwo.setUserId(1);
        userOneDeckTwo.setName("getByUserIdDeckTwo");

        return userOneDeckCollection;
    }

    private Deck SetupGetDeckById()
    {
        Deck deck = new Deck();
        deck.setDeckId(1);
        deck.setUserId(1);
        deck.setName("getByUserIdDeckOne");

        CardCollection deckOneCardCollection = new CardCollection();
        Card card = new Card();
        card.setCardId(2);
        card.setName("Africa");
        card.setAttack(1);
        card.setHealth(1);
        card.setBlue_mana(1);
        card.setDeath_essence(1);
        card.setTaunt(true);
        deckOneCardCollection.AddCard(card);
        deck.setCards(deckOneCardCollection);

        return deck;
    }

    private Deck SetupDeckToAddAndDelete()
    {
        Deck deck = new Deck();
        deck.setDeckId(3);
        deck.setUserId(1);
        deck.setName("deckToAddAndDelete");
        return deck;
    }

    @Test
    void addDeck() {
        Deck expectedDeck = SetupDeckToAddAndDelete();

        Deck deck = deckContainerRepository.AddDeck(expectedDeck);
        this.deckToDeleteId = deck.getDeckId();

        assertEquals(expectedDeck.getName(), deck.getName());
        assertEquals(expectedDeck.getUserId(), deck.getUserId());

        deleteDeck();
    }

    @Test
    void deleteDeck() {
        Deck deck = new Deck();
        deck.setDeckId(deckToDeleteId);

        boolean result = deckContainerRepository.DeleteDeck(deck);

        assertTrue(result);
    }

    @Test
    void getEmptyDecksByUserId() {
        DeckCollection expectedDeckCollection = SetupGetDeckByUserId();

        DeckCollection deckCollection = deckContainerRepository.GetEmptyDecksByUserId(1);

        for (int i = 0; i < expectedDeckCollection.getDecks().size(); i++)
        {
            assertEquals(expectedDeckCollection.getDecks().get(i).getDeckId(), deckCollection.getDecks().get(i).getDeckId());
            assertEquals(expectedDeckCollection.getDecks().get(i).getUserId(), deckCollection.getDecks().get(i).getUserId());
            assertEquals(expectedDeckCollection.getDecks().get(i).getName(), deckCollection.getDecks().get(i).getName());
        }
    }

    @Test
    void getEmptyDeckById() {
        Deck expectedDeck = SetupGetDeckById();

        Deck deck = deckContainerRepository.GetEmptyDeckById(expectedDeck.getDeckId());

        assertEquals(expectedDeck.getDeckId(), deck.getDeckId());
        assertEquals(expectedDeck.getUserId(), deck.getUserId());
        assertEquals(expectedDeck.getName(), deck.getName());
    }

    @Test
    void getDecksByUserId() {
        DeckCollection expectedDeckCollection = SetupGetDeckByUserId();

        DeckCollection deckCollection = deckContainerRepository.GetDecksByUserId(1);

        for (int i = 0; i < expectedDeckCollection.getDecks().size(); i++)
        {
            assertEquals(expectedDeckCollection.getDecks().get(i).getDeckId(), deckCollection.getDecks().get(i).getDeckId());
            assertEquals(expectedDeckCollection.getDecks().get(i).getUserId(), deckCollection.getDecks().get(i).getUserId());
            assertEquals(expectedDeckCollection.getDecks().get(i).getName(), deckCollection.getDecks().get(i).getName());

            for (int j = 0; j < expectedDeckCollection.getDecks().get(i).getCards().getCards().size(); j++)
            {
                assertEquals(expectedDeckCollection.getDecks().get(i).getCards().getCards().get(j).getCardId(),
                        deckCollection.getDecks().get(i).getCards().getCards().get(j).getCardId());

                assertEquals(expectedDeckCollection.getDecks().get(i).getCards().getCards().get(j).getAttack(),
                        deckCollection.getDecks().get(i).getCards().getCards().get(j).getAttack());

                assertEquals(expectedDeckCollection.getDecks().get(i).getCards().getCards().get(j).getHealth(),
                        deckCollection.getDecks().get(i).getCards().getCards().get(j).getHealth());

                assertEquals(expectedDeckCollection.getDecks().get(i).getCards().getCards().get(j).getBlue_mana(),
                        deckCollection.getDecks().get(i).getCards().getCards().get(j).getBlue_mana());

                assertEquals(expectedDeckCollection.getDecks().get(i).getCards().getCards().get(j).getDeath_essence(),
                        deckCollection.getDecks().get(i).getCards().getCards().get(j).getDeath_essence());

                assertEquals(expectedDeckCollection.getDecks().get(i).getCards().getCards().get(j).getTaunt(),
                        deckCollection.getDecks().get(i).getCards().getCards().get(j).getTaunt());

                assertEquals(expectedDeckCollection.getDecks().get(i).getCards().getCards().get(j).getName(),
                        deckCollection.getDecks().get(i).getCards().getCards().get(j).getName());
            }
        }
    }

    @Test
    void getDeckById() {
        Deck expectedDeck = SetupGetDeckById();

        Deck deck = deckContainerRepository.GetDeckById(expectedDeck.getDeckId());

        assertEquals(expectedDeck.getDeckId(), deck.getDeckId());
        assertEquals(expectedDeck.getUserId(), deck.getUserId());
        assertEquals(expectedDeck.getName(), deck.getName());

        for (int i = 0; i < expectedDeck.getCards().getCards().size(); i++)
        {
            assertEquals(expectedDeck.getCards().getCards().get(i).getCardId(), deck.getCards().getCards().get(i).getCardId());
            assertEquals(expectedDeck.getCards().getCards().get(i).getName(), deck.getCards().getCards().get(i).getName());
            assertEquals(expectedDeck.getCards().getCards().get(i).getAttack(), deck.getCards().getCards().get(i).getAttack());
            assertEquals(expectedDeck.getCards().getCards().get(i).getHealth(), deck.getCards().getCards().get(i).getHealth());
            assertEquals(expectedDeck.getCards().getCards().get(i).getBlue_mana(), deck.getCards().getCards().get(i).getBlue_mana());
            assertEquals(expectedDeck.getCards().getCards().get(i).getDeath_essence(), deck.getCards().getCards().get(i).getDeath_essence());
            assertEquals(expectedDeck.getCards().getCards().get(i).getTaunt(), deck.getCards().getCards().get(i).getTaunt());
        }
    }
}