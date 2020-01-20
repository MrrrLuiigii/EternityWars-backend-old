package com.eternitywars.api.Factories.Lobby;

import com.eternitywars.api.DAL.Contexts.Lobby.LobbyContainerSqlContext;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class LobbyContainerFactory
{
    public LobbyContainerSqlContext getLobbyContainerSqlContext()
    {
        return new LobbyContainerSqlContext(new DatabaseConnection());
    }

    public LobbyContainerSqlContext getTestLobbyContainerSqlContext()
    {
        return new LobbyContainerSqlContext(new TestDatabaseConnection());
    }
}

