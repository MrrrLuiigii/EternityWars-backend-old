package com.eternitywars.api.Factories.Deck;

import com.eternitywars.api.DAL.Contexts.Deck.DeckSqlContext;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class DeckFactory
{
    public DeckSqlContext getDeckSqlContext()
    {
        return new DeckSqlContext(new DatabaseConnection());
    }

    public DeckSqlContext getTestDeckSqlContext()
    {
        return new DeckSqlContext(new TestDatabaseConnection());
    }
}
