package com.eternitywars.Logic.WebsocketServer.Collection;

import com.eternitywars.Models.Game;

import java.util.ArrayList;
import java.util.List;

public class GameCollection {
    private static List<Game> activeGames = new ArrayList<>();

    public static List<Game> getActiveGames() {
        return activeGames;
    }

    public static void setActiveGames(List<Game> activeGames) {
        GameCollection.activeGames = activeGames;
    }
}
