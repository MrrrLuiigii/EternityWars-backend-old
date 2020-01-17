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

public class DeckBuilderExecutor implements IExecutor
{
    private DeckBuilderLogic deckBuilderLogic = new DeckBuilderLogic();
    private DeckBuilderContainerLogic deckBuilderContainerLogic = new DeckBuilderContainerLogic();

    private JSONObject message;
    private Session session;

    private WsReturnMessage returnMessage = new WsReturnMessage();

    @Override
    public void Execute(JSONObject message, Session session) throws IOException
    {
        switch (message.getString("Action"))
        {
            case "ADDDECK":
                deckBuilderContainerLogic.AddDeck(message);
                RespondDeckCollection(message);
                break;
            case "DELETEDECK":
                deckBuilderContainerLogic.DeleteDeck(message);
                RespondDeckCollection(message);
                break;

            case "GETALLDECKS":
                RespondDeckCollection(message);
                break;
            case "GETBUILDERDECKBYID":
                RespondBuilderDeck(message);
                break;

            case "GETDECKBYID":
                Deck deck = deckBuilderContainerLogic.GetDeckById(message);
                SendWebsocketReturnMessage("GETDECKBYID", deck);
                break;

            case "ADDCARD":
                deckBuilderLogic.AddCard(message);
                RespondBuilderDeck(message);
                break;
            case "REMOVECARD":
                deckBuilderLogic.RemoveCard(message);
                RespondBuilderDeck(message);
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

        SendWebsocketReturnMessage("GETBUILDERDECKBYID", returnDeck);
    }

    private void RespondDeckCollection(JSONObject jsonObject) throws IOException
    {
        DeckCollection emptyDeckCollection = deckBuilderContainerLogic.GetAllEmptyDecks(jsonObject);
        DeckCollection deckCollection = deckBuilderContainerLogic.GetAllDecks(jsonObject);

        for(Deck ed : emptyDeckCollection.getDecks())
        {
            if (!CheckDeckCollectionContainsDeck(deckCollection, ed.getDeckId()))
            {
                ed.setCards(new CardCollection());
                deckCollection.AddDeck(ed);
            }
        }

        SendWebsocketReturnMessage("GETALLDECKS", deckCollection);
    }

    private boolean CheckDeckCollectionContainsDeck(DeckCollection deckCollection, int deckId)
    {
        for (Deck d : deckCollection.getDecks())
        {
            if (d.getDeckId() == deckId)
            {
                return true;
            }
        }

        return false;
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
