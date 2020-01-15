package com.eternitywars.Logic.WebsocketServer.Executors;

import com.eternitywars.Logic.Card.CardContainerLogic;
import com.eternitywars.Logic.WebsocketServer.Models.WsReturnMessage;
import com.eternitywars.Models.CardCollection;
import com.eternitywars.Models.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.eclipse.jetty.websocket.api.Session;
import org.json.JSONObject;
import java.io.IOException;

public class CardExecutor implements IExecutor
{
    private CardContainerLogic cardContainerLogic = new CardContainerLogic();

    private JSONObject message;
    private Session session;

    public CardExecutor(JSONObject message, Session session)
    {
        this.message = message;
        this.session = session;
    }

    @Override
    public void Execute(JSONObject message, Session session) throws IOException
    {
        GsonBuilder gs = new GsonBuilder();
        gs.serializeNulls();
        Gson gson = gs.create();

        CardCollection cardCollection = new CardCollection();

        String token = message.getString("Token");

        switch (message.getString("Action"))
        {
            case "GetByUserId":
                User user = gson.fromJson(message.getJSONObject("Content").toString(), User.class);
                cardCollection = cardContainerLogic.GetCardsByUserId(user, token);
                break;
        }

        WsReturnMessage returnMessage = new WsReturnMessage();
        returnMessage.setAction("GETCARDCOLLECTION");
        returnMessage.setContent(cardCollection);
        session.getRemote().sendString(gson.toJson(returnMessage));
    }

    @Override
    public void run()
    {
        try
        {
            synchronized (message)
            {
                Execute(message, session);
            }
        } catch (IOException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
