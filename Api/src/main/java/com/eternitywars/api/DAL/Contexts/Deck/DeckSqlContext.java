package com.eternitywars.api.DAL.Contexts.Deck;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Deck.IDeckContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeckSqlContext implements IDeckContext
{
    private DatabaseConnection dbc = new DatabaseConnection();



    public boolean AddCard(Deck deck, Card card)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "insert into `deck_card`(`deck_id`, `card_id`) values(?, ?);";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, deck.getDeckId());
                pst.setInt(2, card.getCardId());
                pst.executeQuery();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public boolean DeleteCard(Deck deck, Card card)
    {
        return false;
    }

    public boolean UpdateDeck(Deck deck)
    {
        return false;
    }
}
