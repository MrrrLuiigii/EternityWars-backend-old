package com.eternitywars.api.Factories.Lobby;

import com.eternitywars.api.DAL.Contexts.Lobby.LobbySqlContext;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class LobbyFactory
{
    public LobbySqlContext getLobbySqlContext()
    {
        return new LobbySqlContext(new DatabaseConnection());
    }

    public LobbySqlContext getTestLobbySqlContext()
    {
        return new LobbySqlContext(new TestDatabaseConnection());
    }
}
