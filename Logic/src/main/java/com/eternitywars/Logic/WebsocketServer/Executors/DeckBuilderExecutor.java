package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.DeckBuilder.DeckBuilderContainerLogic;
import com.eternitywars.Logic.DeckBuilder.DeckBuilderLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;

import java.io.IOException;

public class DeckBuilderExecutor implements IExecutor  {

    private DeckBuilderLogic deckBuilderLogic = new DeckBuilderLogic();
    private DeckBuilderContainerLogic deckBuilderContainerLogic = new DeckBuilderContainerLogic();

    private JSONObject message;
    private Session session;

    public boolean answer;

    WsReturnMessage returnMessage = new WsReturnMessage();

    @Override
    public void Execute(JSONObject message, Session session) throws IOException {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        switch (message.getString("Action")) {
            case "ADDDECK":
               deckBuilderContainerLogic.AddDeck(message);
               RespondDeckCollection(message);
                break;
            case "GETALLDECK":
                RespondDeckCollection(message);
                break;
            case "GETALLEMPTYDECKS":
                RespondEmptyDeckCollection(message);
                break;
            case "GETBUILDERDECKBYID":
                RespondBuilderDeck(message);
                break;
            case "GETDECKBYID":
                Deck deck = deckBuilderContainerLogic.GetDeckById(message);

                returnMessage.setAction("GETDECKBYID");
                returnMessage.setContent(deck);
                session.getRemote().sendString(new JSONObject(deck).toString());
                break;
            case "DELETEDECK":
                deckBuilderContainerLogic.DeleteDeck(message);
                RespondDeckCollection(message);
                break;
            case "SAVEDECK":
                deckBuilderContainerLogic.SaveDeck(message);
                RespondDeckCollection(message);
                break;
            case "ADDCARD":
                answer = deckBuilderLogic.AddCard(message);
                returnMessage.setAction("ADDCARD");
                returnMessage.setContent(answer);
                session.getRemote().sendString(new JSONObject(answer).toString());
                RespondBuilderDeck(message);
                break;
            case "REMOVECARD":
                answer = deckBuilderLogic.RemoveCard(message);
                returnMessage.setAction("REMOVECARD");
                returnMessage.setContent(answer);
                session.getRemote().sendString(new JSONObject(answer).toString());
                RespondBuilderDeck(message);
                break;
            case "GETALLCARDSBYACCOUNT":
                CardCollection collection = deckBuilderContainerLogic.GetAllCardsByAccount(message);

                returnMessage.setAction("GETALLCARDSBYACCOUNT");
                returnMessage.setContent(collection);
                session.getRemote().sendString(new JSONObject(collection).toString());
                break;
        }
    }

    private void RespondBuilderDeck(JSONObject jsonObject) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        String token = jsonObject.getString("Token");

        Deck deck = gson.fromJson(jsonObject.getJSONObject("Content").toString(), Deck.class);
        Deck returnDeck = deckBuilderContainerLogic.GetDeckById(deck.getDeckId(), token);

        if (returnDeck.getDeckId() == 0)
        {
            returnDeck = deckBuilderContainerLogic.GetEmptyDeckById(deck.getDeckId(), token);
            returnDeck.setCards(new CardCollection());
        }

        //todo refactor to use this method everywhere
//        SendWebsocketReturnMessage("GETBUILDERDECKBYID", returnDeck);

        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction("GETBUILDERDECKBYID");
        returnMessage.setContent(returnDeck);
        session.getRemote().sendString(gson.toJson(returnMessage));
    }

    private void RespondDeckCollection(JSONObject jsonObject) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        DeckCollection deckCollection = deckBuilderContainerLogic.GetAllDecks(jsonObject);

        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction("GETALLDECK");
        returnMessage.setContent(deckCollection);
        session.getRemote().sendString(gson.toJson(returnMessage));
    }

    private void RespondEmptyDeckCollection(JSONObject jsonObject) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        DeckCollection deckCollection = deckBuilderContainerLogic.GetAllEmptyDecks(jsonObject);

        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction("GETALLDECK");
        returnMessage.setContent(deckCollection);
        session.getRemote().sendString(gson.toJson(returnMessage));
    }

    private void SendWebsocketReturnMessage(String action, Object content) throws IOException
    {
        WsReturnMessage wsReturnMessage = new WsReturnMessage();
        wsReturnMessage.setAction(action);
        wsReturnMessage.setContent(content);
        session.getRemote().sendString(new JSONObject(wsReturnMessage).toString());
    }



    public DeckBuilderExecutor(JSONObject message, Session session)
    {
        this.message = message;
        this.session = session;
    }

    @Override
    public void run() {
        try {
            Execute(message, session);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
