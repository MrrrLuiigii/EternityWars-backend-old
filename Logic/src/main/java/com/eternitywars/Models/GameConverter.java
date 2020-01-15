package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class GameConverter
{
    public Game ConvertToGame(Lobby lobby)
    {
        List<Player> players = new ArrayList<Player>();
        players.add(lobby.getPlayerOne());
        players.add(lobby.getPlayerTwo());
        players.get(0).setCardsInHand(new ArrayList<>());
        players.get(1).setCardsInHand(new ArrayList<>());
        players.get(0).setHero(new Hero());
        players.get(1).setHero(new Hero());
        Game game = new Game();
        game.setConnectedPlayers(players);
        game.getConnectedPlayers().get(0).setDeck(lobby.getPlayerOne().getDeck());
        game.getConnectedPlayers().get(1).setDeck(lobby.getPlayerTwo().getDeck());
        return game;
    }
}
