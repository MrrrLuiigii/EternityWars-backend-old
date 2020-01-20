package com.eternitywars.api.Factories.Deck;

import com.eternitywars.api.DAL.Contexts.Deck.DeckContainerSqlContext;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class DeckContainerFactory
{
    public DeckContainerSqlContext getDeckContainerSqlContext()
    {
        return new DeckContainerSqlContext(new DatabaseConnection());
    }

    public DeckContainerSqlContext getTestDeckContainerSqlContext()
    {
        return new DeckContainerSqlContext(new TestDatabaseConnection());
    }
}
