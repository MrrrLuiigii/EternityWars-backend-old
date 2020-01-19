package com.eternitywars.Logic;

import com.eternitywars.Models.Game;
import com.eternitywars.Models.Lobby;
import com.google.gson.Gson;
import org.json.JSONObject;

public class ObjectConverter
{
    public static Game JSONtoGame(JSONObject jsonObject)
    {
        Gson gson = new Gson();
        Game game = gson.fromJson(jsonObject.getJSONObject("Content").toString(), Game.class);
        return game;
    }

    public Lobby JSONtoLobby(JSONObject jsonObject)
    {
        Gson gson = new Gson();
        Lobby lobby = gson.fromJson(jsonObject.getJSONObject("Content").toString(), Lobby.class);
        return lobby;
    }
}
