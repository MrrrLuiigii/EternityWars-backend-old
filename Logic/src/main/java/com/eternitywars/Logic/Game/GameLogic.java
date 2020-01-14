package com.eternitywars.Logic.Game;

import com.eternitywars.Logic.ObjectConverter;
import com.eternitywars.Logic.WebsocketServer.Collection.UserCollection;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic
{
    ObjectConverter objectConverter;
    Random rnd = new Random();

    public void LaunchGame(Lobby lobby)
    {
        GameConverter gameConverter = new GameConverter();

        Game game = GetFirstThreeCards(ChooseFirstTurn(gameConverter.ConvertToGame(lobby)));
        try {
            BroadCastGame(game);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Game ChooseFirstTurn(Game game)
    {
        int picker = rnd.nextInt(1);
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
        game.getConnectedPlayers().get(0).setCardsInDeck(new ArrayList<>());
        game.getConnectedPlayers().get(1).setCardsInDeck(new ArrayList<>());
        game.getConnectedPlayers().get(0).setCardsInHand(new ArrayList<>());
        game.getConnectedPlayers().get(1).setCardsInHand(new ArrayList<>());
        return game;
    }

   public Game GetFirstThreeCards(Game game)
   {
       for(Player player: game.getConnectedPlayers())
       {
           Deck pickableDeck = player.getDeck();

           for(int i = 0; i< 3; i++)
           {
               int cardId = rnd.nextInt(pickableDeck.getCards().getCards().size());
               Card card = pickableDeck.getCards().getCards().get(cardId);
               pickableDeck.getCards().getCards().remove(cardId);
               player.getCardsInHand().add(card);
           }
       }
       return game;
   }

   private void BroadCastGame(Game game) throws IOException {
       GsonBuilder gs = new GsonBuilder();
       gs.serializeNulls();
       Gson gson = gs.create();
       WsReturnMessage returnMessage = new WsReturnMessage();
       returnMessage.setAction("LAUNCHGAME");
       returnMessage.setContent(game);

       for (User u : UserCollection.getConnectedUsers()){
           if(game.getConnectedPlayers().get(0) != null){
               if(u.getUsername().equals(game.getConnectedPlayers().get(0).getUsername())){
                   u.getSession().getRemote().sendString(gson.toJson(returnMessage));
               }
           }
           if(game.getConnectedPlayers().get(1) != null){
               if(u.getUsername().equals(game.getConnectedPlayers().get(1).getUsername())){
                   u.getSession().getRemote().sendString(gson.toJson(returnMessage));
               }
           }
       }
   }



}
