package com.eternitywars.Logic.Game;

import com.eternitywars.Logic.ObjectConverter;
import com.eternitywars.Models.*;
import com.google.gson.Gson;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic
{
    ObjectConverter objectConverter;
    Random rnd = new Random();


    public Game LaunchGame(JSONObject jsonObject)
    {
        Lobby lobby = objectConverter.JSONtoLobby(jsonObject);
        GameConverter gameConverter = new GameConverter();
        Game game = GetFirstThreeCards(ChooseFirstTurn(gameConverter.ConvertToGame(lobby)));
        return game;
    }

    public Game ChooseFirstTurn(Game game)
    {
        int picker = rnd.nextInt(2);
        if(picker == 1)
        {
            game.setPlayerTurn(game.getConnectedPlayers().get(0).getUserId());
            game.getConnectedPlayers().get(1).setDeathEssence(1);
        }
        else
        {
            game.setPlayerTurn(game.getConnectedPlayers().get(1).getUserId());
            game.getConnectedPlayers().get(0).setDeathEssence(1);

        }
        game.getConnectedPlayers().get(0).setMana(1);
        game.getConnectedPlayers().get(1).setMana(1);
        return game;
    }

   public Game GetFirstThreeCards(Game game)
   {
       List<Card> firstCardsPlayer1 = new ArrayList<>();
       List<Card> firstCardsPlayer2 = new ArrayList<>();
       for(Player player: game.getConnectedPlayers())
       {
           List<Card> pickableDeck = player.getCardsInDeck();

           for(int i = 0; i< 3; i++)
           {
               int cardId = rnd.nextInt(30 - i);
               Card card = pickableDeck.get(cardId);
               pickableDeck.remove(cardId);
               player.getCardsInHand().add(card);
           }
       }
       return game;
   }


}
