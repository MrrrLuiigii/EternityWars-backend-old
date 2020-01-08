package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class GameConverter
{
    public Game ConvertToGame(Lobby lobby)
    {
        List<Player> players = new ArrayList<Player>();
        players.add(lobby.getPlayerOne());
        players.add(lobby.getPLayerTwo());
        Game game = new Game();
        game.setConnectedPlayers(players);
        return game;
    }
}
