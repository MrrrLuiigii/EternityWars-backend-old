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
		game.getConnectedPlayers().get(1).setUserId(1);
		game.getConnectedPlayers().get(0).setDeck(testdeck);
		game.getConnectedPlayers().get(1).setDeck(testdeck);
		game.getConnectedPlayers().get(0).setHero(new Hero());
		game.getConnectedPlayers().get(1).setHero(new Hero());

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

	}







}
