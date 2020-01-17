package com.eternitywars.Logic.Game;

import com.eternitywars.Logic.ObjectConverter;
import com.eternitywars.Logic.WebsocketServer.Collection.UserCollection;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import sun.security.util.SecurityProperties;

import java.io.IOException;
import java.util.ArrayList;
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
        int picker = rnd.nextInt(2);
        if(picker == 1)
        {
            game.setPlayerTurn(game.getConnectedPlayers().get(0).getUserId());
            game.getConnectedPlayers().get(1).getHero().setDeathessence(1);
        }
        else
        {
            game.setPlayerTurn(game.getConnectedPlayers().get(1).getUserId());
            game.getConnectedPlayers().get(0).getHero().setDeathessence(1);
        }
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
           player.setCardsInDeck(pickableDeck.getCards().getCards());
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
       game.getConnectedPlayers().get(0).getHero().setMana(game.getConnectedPlayers().get(0).getHero().getMaxMana());
       game.getConnectedPlayers().get(1).getHero().setMana(game.getConnectedPlayers().get(1).getHero().getMaxMana());

       for (User u : UserCollection.getConnectedUsers()){
           if(game.getConnectedPlayers().get(0) != null){
               if(u.getUsername().equals(game.getConnectedPlayers().get(0).getUsername())){
                   u.getSession().getRemote().sendString(gson.toJson(returnMessage));
               }
           }
           if(game.getConnectedPlayers().get(1) != null){
               if(u.getUsername().equals(game.getConnectedPlayers().get(1).getUsername())){
                   returnMessage.setContent(SwapPlayers(game));
                   u.getSession().getRemote().sendString(gson.toJson(returnMessage));
               }
           }
       }
   }

   private Game SwapPlayers(Game game)
   {
    Player tempPlayer = game.getConnectedPlayers().get(0);
    game.getConnectedPlayers().set(0, game.getConnectedPlayers().get(1));
    game.getConnectedPlayers().set(1, tempPlayer);
    return game;
   }

   public Game EndTurn(Game game){
        ClearError(game);
        IncreaseMaxMana(game);
        IncreaseMaxDeathessence(game);
        RechargeMana(game);
        DrawCard(game);
       AwakeCards(game);

        if(game.getConnectedPlayers().get(0).getUserId() == game.getPlayerTurn()){
            game.setPlayerTurn(game.getConnectedPlayers().get(1).getUserId());
            return game;
        }

        game.setPlayerTurn(game.getConnectedPlayers().get(0).getUserId());
        return game;
   }

   private Game IncreaseMaxMana(Game game){
        if(game.getConnectedPlayers().get(0).getHero().getMaxMana() != 10){
            game.getConnectedPlayers().get(0).getHero().setMaxMana(game.getConnectedPlayers().get(0).getHero().getMaxMana() + 1);
            return game;
        }
        return game;
   }

   private Game IncreaseMaxDeathessence(Game game){
       if(game.getConnectedPlayers().get(0).getHero().getMaxDeathessence() != 10){
           game.getConnectedPlayers().get(0).getHero().setMaxDeathessence(game.getConnectedPlayers().get(0).getHero().getMaxDeathessence() + 1);
           return game;
       }
       return game;
   }

   public Game RechargeMana(Game game){
        game.getConnectedPlayers().get(0).getHero().setMana(game.getConnectedPlayers().get(0).getHero().getMaxMana());
        return game;
   }

   public void ConsumeResources(Game game, int manaCost, int deathEssenceCost)
   {
       game.getConnectedPlayers().get(0).getHero().setMana(game.getConnectedPlayers().get(0).getHero().getMana() - manaCost);
       game.getConnectedPlayers().get(0).getHero().setDeathessence(game.getConnectedPlayers().get(0).getHero().getDeathessence() - deathEssenceCost);
   }



   public Game ObtainDeathessence(Game game){
        int currentDeathessence = game.getConnectedPlayers().get(0).getHero().getDeathessence();
        if(currentDeathessence < game.getConnectedPlayers().get(0).getHero().getMaxDeathessence()){
            game.getConnectedPlayers().get(0).getHero().setDeathessence(currentDeathessence + 1 );
        }
        return game;
   }

   public Game AttackCard(Game game, int attacker, int target) {
        if(!CheckForTurn(game))
        {
            return game;
        }

        CardSlot attackersCardslot = game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(attacker);
        CardSlot targetCardslot = game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(target);

       if(!attackersCardslot.getCard().getIssleeping())
       {

           targetCardslot.setCard(CalculateRemainingHp(attackersCardslot.getCard(),targetCardslot.getCard()));
           attackersCardslot.setCard(CalculateRemainingHp(targetCardslot.getCard(), attackersCardslot.getCard()));

           if(targetCardslot.getCard().getHealth() <= 0){
               game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().get(target).setCard(null);
               game = ObtainDeathessence(game);
           }
           else {
               game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList().set(target, targetCardslot);
           }

           if(attackersCardslot.getCard().getHealth() <= 0){
               game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(attacker).setCard(null);
           }
           else{
               game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().set(attacker, attackersCardslot);
               attackersCardslot.getCard().setIssleeping(true);
           }

           return game;
       }
       game.getConnectedPlayers().get(0).setError("Bende gij nie heulemaal wakker ofzo");
       return game;
   }

   public Game AttackHero(Game game, int CardToAttackHeroWith) throws IOException {
       if(game.getPlayerTurn() != game.getConnectedPlayers().get(0).getUserId())
       {
           game.getConnectedPlayers().get(0).setError("blijf met je klauwe van die kaart af lijpo");
           return game;
       }
        Card attackerCard = game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(CardToAttackHeroWith).getCard();
        if(!attackerCard.getIssleeping())
        {
            int currentHp = game.getConnectedPlayers().get(1).getHero().getHp();
            game.getConnectedPlayers().get(1).getHero().setHp( currentHp - attackerCard.getAttack());
            attackerCard.setIssleeping(true);
            if(game.getConnectedPlayers().get(1).getHero().getHp() <= 0 ){
                EndGame(game);
            }
            return game;
        }
        game.getConnectedPlayers().get(0).setError("Bende gij nie heulemaal wakker ofzo");
       return game;
   }

   public void RemoveCardFromHand(Game game, int cardToPlay)
   {
       game.getConnectedPlayers().get(0).getCardsInHand().remove(cardToPlay);
   }

   public Game PlayCard(Game game, int cardToPlay, int target){
        if(!CheckForTurn(game))
        {
            return game;
        }
        int currentMana = game.getConnectedPlayers().get(0).getHero().getMana();
        int currentDeathEssence = game.getConnectedPlayers().get(0).getHero().getDeathessence();
        Card playablecard = game.getConnectedPlayers().get(0).getCardsInHand().get(cardToPlay);

        if(currentMana < playablecard.getBlue_mana()){
            game.getConnectedPlayers().get(0).setError("You dont have enough resources to do that!");
            return game;
        }
       if(currentDeathEssence < playablecard.getDeath_essence()){
           game.getConnectedPlayers().get(0).setError("You dont have enough resources to do that!");
           return game;
       }
        ConsumeResources(game, playablecard.getBlue_mana(), playablecard.getDeath_essence());
        RemoveCardFromHand(game, cardToPlay);

        //todo death essence check
        game.getConnectedPlayers().get(0).getBoardRows().getCardSlotList().get(target).setCard(playablecard);
        return game;
   }

   private void EndGame(Game game) throws IOException {
       SendGame(game, "ENDGAME");
   }

   private Card CalculateRemainingHp(Card attacker, Card target){
        target.setHealth(target.getHealth() - attacker.getAttack());
        return target;
   }

   private Game DrawCard(Game game){

       Deck pickableDeck = game.getConnectedPlayers().get(1).getDeck();
       int cardId = rnd.nextInt(pickableDeck.getCards().getCards().size());
       Card card = pickableDeck.getCards().getCards().get(cardId);
        if(CanDraw(game))
        {
            pickableDeck.getCards().getCards().remove(cardId);
            game.getConnectedPlayers().get(1).getCardsInHand().add(card);
            game.getConnectedPlayers().get(1).setCardsInDeck(pickableDeck.getCards().getCards());
            return game;
        }
       game.getConnectedPlayers().get(1).setError("Your hand is full!");
       return game;
   }

   private boolean CanDraw(Game game)
   {
       if(game.getConnectedPlayers().get(1).getCardsInHand().size() < 9)
       {
           return true;
       }
       return false;
   }


   public void SendGame(Game game, String Action) throws IOException {
       GsonBuilder gs = new GsonBuilder();
       gs.serializeNulls();
       Gson gson = gs.create();
       WsReturnMessage returnMessage = new WsReturnMessage();
       returnMessage.setAction(Action);
       returnMessage.setContent(game);


       for (User u : UserCollection.getConnectedUsers()){
           if(game.getConnectedPlayers().get(0) != null){
               if(u.getUsername().equals(game.getConnectedPlayers().get(0).getUsername())){
                   u.getSession().getRemote().sendString(gson.toJson(returnMessage));
               }
           }
           if(game.getConnectedPlayers().get(1) != null){
               if(u.getUsername().equals(game.getConnectedPlayers().get(1).getUsername())){
                   returnMessage.setContent(SwapPlayers(game));
                   u.getSession().getRemote().sendString(gson.toJson(returnMessage));
               }
           }
       }
   }

   public void AwakeCards(Game game)
   {

       for(CardSlot cardslot : game.getConnectedPlayers().get(1).getBoardRows().getCardSlotList())
       {
           if(cardslot.getCard() != null)
           {
               cardslot.getCard().setIssleeping(false);
           }
       }
   }

    private boolean CheckForTurn(Game game)
    {
        if(game.getPlayerTurn() != game.getConnectedPlayers().get(0).getUserId())
        {
            game.getConnectedPlayers().get(0).setError("blijf met je klauwe van die kaart af lijpo");
            return false;
        }
        return true;
    }


   private Game ClearError(Game game)
   {
       game.getConnectedPlayers().get(0).setError(null);
       game.getConnectedPlayers().get(1).setError(null);
       return game;
   }
}
