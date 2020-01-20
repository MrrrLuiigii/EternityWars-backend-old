package com.eternitywars.api.Factories.Card;

import com.eternitywars.api.DAL.Contexts.Card.CardContainerSqlContext;
import com.eternitywars.api.DAL.Contexts.User.UserContainerSqlContext;
import com.eternitywars.api.DAL.Repositories.Card.CardContainerRepository;
import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;

public class CardContainerFactory
{
    public CardContainerSqlContext getCardContainerSqlContext()
    {
        return new CardContainerSqlContext(new DatabaseConnection());
    }

    public CardContainerSqlContext getTestCardContainerSqlContext()
    {
        return new CardContainerSqlContext(new TestDatabaseConnection());
    }
}
