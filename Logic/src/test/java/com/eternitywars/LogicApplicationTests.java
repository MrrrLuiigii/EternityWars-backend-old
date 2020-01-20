package com.eternitywars;

import com.eternitywars.Logic.Game.GameLogic;
import com.eternitywars.Models.*;
import com.eternitywars.Models.Enums.AccountStatus;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.constraints.AssertTrue;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LogicApplicationTests {
	GameLogic gameLogic = new GameLogic();
	Game game;
	Lobby lobby = new Lobby();
	Deck testdeck = new Deck();


	
	public LogicApplicationTests()
	{
		testdeck.setCards(new CardCollection());
		for(int i = 0; i<10; i++)
		{
			testdeck.getCards().getCards().add(new Card());
		}
		game = new Game();
		game.setConnectedPlayers(new ArrayList<>());
		game.getConnectedPlayers().add(new Player());
		game.getConnectedPlayers().add(new Player());
		game.getConnectedPlayers().get(0).setUsername("wiebe");
		game.getConnectedPlayers().get(1).setUsername("nicky");
		game.getConnectedPlayers().get(0).setUserId(1);
		game.getConnectedPlayers().get(1).setUserId(2);
		game.getConnectedPlayers().get(0).setDeck(testdeck);
		game.getConnectedPlayers().get(1).setDeck(testdeck);
		game.getConnectedPlayers().get(0).setHero(new Hero());
		game.getConnectedPlayers().get(1).setHero(new Hero());
		game.getConnectedPlayers().get(0).setBoardRow(new BoardRow());
		game.getConnectedPlayers().get(1).setBoardRow(new BoardRow());

		lobby.setPlayerOne(new Player());
		lobby.setPlayerTwo(new Player());
		lobby.getPlayerOne().setDeck(testdeck);
		lobby.getPlayerTwo().setDeck(testdeck);

		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		Card testcard2 = new Card(1, "ron", 10, 1, 4, 4, false, false);
	}



	@Test
	void EndTurn() throws IOException
	{
		game.setPlayerTurn(1);
		game = gameLogic.EndTurn(game);
	}

	@Test
	void EndTurnplayerturn2() throws IOException
	{
		game.setPlayerTurn(2);
		game = gameLogic.EndTurn(game);
	}

	@Test
	void IncreaseManaAndDeathEssenceMax10() throws IOException
	{
		game.getConnectedPlayers().get(0).getHero().setMaxMana(10);
		game.getConnectedPlayers().get(0).getHero().setMaxDeathessence(10);
		game = gameLogic.EndTurn(game);
		assertEquals(game.getConnectedPlayers().get(0).getHero().getMaxMana(), 10);
		assertEquals(game.getConnectedPlayers().get(0).getHero().getMaxDeathessence(), 10);
	}

	@Test
	void DrawCardWithFullHand() throws IOException
	{
		for(int i = 0; i< 10;i++)
		{
			game.getConnectedPlayers().get(1).getCardsInHand().add(new Card());
		}
		game = gameLogic.EndTurn(game);
	}

	@Test
	void LaunchGame()
	{
		gameLogic.LaunchGame(lobby);
	}

	@Test
	void ObtainDeathEssence()
	{
		game.getConnectedPlayers().get(0).getHero().setMaxDeathessence(10);
		game.getConnectedPlayers().get(0).getHero().setDeathessence(1);
		game = gameLogic.ObtainDeathessence(game);
		assertEquals(2, game.getConnectedPlayers().get(0).getHero().getDeathessence());
	}

	@Test
	void ConsumeResource()
	{
		game.getConnectedPlayers().get(0).getHero().setMana(1);
		gameLogic.ConsumeResources(game, 1,0);
		assertEquals(game.getConnectedPlayers().get(0).getHero().getMana(), 0);
	}

	@Test
	void AttackCardFail() throws IOException {

		game = gameLogic.AttackCard(game, 0 , 0);
	}

	@Test
	void AttackCardWithoutTaunt() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, false);
		Card testcard2 = new Card(1, "ron", 10, 1, 4, 4, false, false);
		CardSlot testcardslot = new CardSlot();
		testcardslot.setCard(testcard);

		game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(0).setCard(testcard);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(0).setCard(testcard2);
		game.setPlayerTurn(1);
		game = gameLogic.AttackCard(game, 0 , 0);

		assertEquals(9, game.getConnectedPlayers().get(0).getBoardRow().getCardSlotList().get(0).getCard().getHealth());
	}

	@Test
	void AttackCardWithTaunt() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		Card testcard2 = new Card(1, "ron", 10, 1, 4, 4, false, true);
		CardSlot testcardslot = new CardSlot();
		testcardslot.setCard(testcard);

		game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(0).setCard(testcard);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(0).setCard(testcard2);
		game.setPlayerTurn(1);
		game = gameLogic.AttackCard(game, 0 , 0);

		assertEquals(9, game.getConnectedPlayers().get(0).getBoardRow().getCardSlotList().get(0).getCard().getHealth());
	}

	@Test
	void AttackCardfail() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		Card testcard2 = new Card(1, "ron", 10, 1, 4, 4, false, true);
		Card testcard3 = new Card(1, "ron", 10, 1, 4, 4, false, false);
		CardSlot testcardslot = new CardSlot();
		testcardslot.setCard(testcard);

		game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(0).setCard(testcard);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(0).setCard(testcard2);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(1).setCard(testcard3);
		game.setPlayerTurn(1);
		game = gameLogic.AttackCard(game, 0 , 1);

		assertEquals(10, game.getConnectedPlayers().get(0).getBoardRow().getCardSlotList().get(0).getCard().getHealth());
	}

	@Test
	void AttackCardSleep() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, true, true);
		Card testcard2 = new Card(1, "ron", 10, 1, 4, 4, false, true);
		Card testcard3 = new Card(1, "ron", 10, 1, 4, 4, false, false);
		CardSlot testcardslot = new CardSlot();
		testcardslot.setCard(testcard);

		game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(0).setCard(testcard);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(0).setCard(testcard2);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(1).setCard(testcard3);
		game.setPlayerTurn(1);
		game = gameLogic.AttackCard(game, 0 , 1);

		assertEquals(10, game.getConnectedPlayers().get(0).getBoardRow().getCardSlotList().get(0).getCard().getHealth());
	}

	@Test
	void AttackHeroNoTurn() throws IOException {
		game.setPlayerTurn(0);
		game = gameLogic.AttackHero(game, 0 , "example");
	}

	@Test
	void AttackHeroCardWithTaunt() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		Card testcard2 = new Card(1, "ron", 10, 1, 4, 4, false, true);
		CardSlot testcardslot = new CardSlot();
		testcardslot.setCard(testcard);

		game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(0).setCard(testcard);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(0).setCard(testcard2);
		game.setPlayerTurn(1);
		game = gameLogic.AttackHero(game, 0 , "example");
	}

	@Test
	void AttackHeroCardWithSleeping() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, true, true);
		Card testcard2 = new Card(1, "ron", 10, 1, 4, 4, false, true);
		CardSlot testcardslot = new CardSlot();
		testcardslot.setCard(testcard);

		game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(0).setCard(testcard);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(0).setCard(testcard2);
		game.setPlayerTurn(1);
		game = gameLogic.AttackHero(game, 0 , "example");
	}

	@Test
	void AttackHero() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		Card testcard2 = new Card(1, "ron", 10, 1, 4, 4, false, false);
		CardSlot testcardslot = new CardSlot();
		testcardslot.setCard(testcard);

		game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(0).setCard(testcard);
		game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(0).setCard(testcard2);
		game.setPlayerTurn(1);
		game = gameLogic.AttackHero(game, 0 , "example");
	}

	@Test
	void RemoveCardFromHand()
	{
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		game.getConnectedPlayers().get(0).setCardsInHand(new ArrayList<>());
		game.getConnectedPlayers().get(0).getCardsInHand().add(testcard);
		gameLogic.RemoveCardFromHand(game, 0);
		assertEquals(0, game.getConnectedPlayers().get(0).getCardsInHand().size());
	}

	@Test
	void PLayCardNoTurn() throws IOException {
		game.setPlayerTurn(0);
		game = gameLogic.PlayCard(game, 0 , 1);
	}

	@Test
	void PlayCard() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		game.setPlayerTurn(1);
		game.getConnectedPlayers().get(0).getHero().setMana(4);
		game.getConnectedPlayers().get(0).getHero().setDeathessence(4);
		game.getConnectedPlayers().get(0).getCardsInHand().add(testcard);
		game = gameLogic.PlayCard(game, 0 , 1);
		assertEquals(0, game.getConnectedPlayers().get(0).getHero().getMana());
	}

	@Test
	void PlayCardNoMana() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		game.setPlayerTurn(1);
		game.getConnectedPlayers().get(0).getHero().setMana(0);
		game.getConnectedPlayers().get(0).getCardsInHand().add(testcard);
		game = gameLogic.PlayCard(game, 0 , 1);

	}

	@Test
	void PlayCardNoDE() throws IOException {
		Card testcard = new Card(1, "ron", 10, 1, 4, 4, false, true);
		game.setPlayerTurn(1);
		game.getConnectedPlayers().get(0).getHero().setMana(4);
		game.getConnectedPlayers().get(0).getCardsInHand().add(testcard);
		game = gameLogic.PlayCard(game, 0 , 1);

	}
}
