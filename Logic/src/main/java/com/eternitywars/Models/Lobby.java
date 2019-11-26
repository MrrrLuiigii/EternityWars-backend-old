package com.eternitywars.Models;

import java.util.ArrayList;
import java.util.List;

public class Lobby
{
    int lobbyId;
    List<Account> accounts;

    public Lobby(int lobbyId)
    {
        this.lobbyId = lobbyId;
        this.accounts = new ArrayList<>();
    }
}
