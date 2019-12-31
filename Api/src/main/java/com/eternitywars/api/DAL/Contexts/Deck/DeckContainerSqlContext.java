package com.eternitywars.api.DAL.Contexts.Deck;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Deck.IDeckContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeckContainerSqlContext implements IDeckContainerContext
{
    private DatabaseConnection dbc = new DatabaseConnection();



    public Deck AddDeck(Deck deck)
    {
        return null;
    }

    public boolean DeleteDeck(Deck deck)
    {
        return false;
    }

    public DeckCollection GetAllDecksByUserId(int userId)
    {
        DeckCollection deckCollection = new DeckCollection();

        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "SELECT " +
                        "d.`id` AS deck_id, d.`name` AS deck_name, d.`user_id`, " +
                        "c.`id` AS card_id, c.`name` AS card_name, c.`health`, c.`attack`, c.`blue_mana`, c.`death_essence` " +
                    "FROM `deck` AS d " +
                    "INNER JOIN `deck_card` AS dc " +
                    "ON d.`id` = dc.`deck_id` " +
                    "INNER JOIN `card` AS c " +
                    "ON dc.`card_id` = c.`id` " +
                    "where d.`user_id` = ?;";

            try(PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, userId);

                try(ResultSet rs = pst.executeQuery())
                {
                    Deck completeDeck = null;

                    while(rs.next())
                    {
                        Deck deck = new Deck(0);
                        deck.setDeckId(rs.getInt("deck_id"));
                        deck.setName(rs.getString("name"));
                        deck.setUserId(rs.getInt("user_id"));
                        CardCollection cardCollection = new CardCollection();

                        if (rs.getInt("deck_id") == deck.getDeckId())
                        {
                            Card card = new Card();
                            card.setCardId(rs.getInt("card_id"));
                            card.setName(rs.getString("card_name"));
                            card.setHealth(rs.getInt("health"));
                            card.setAttack(rs.getInt("attack"));
                            card.setBlue_mana(rs.getInt("blue_mana"));
                            card.setDeath_essence(rs.getInt("death_essence"));
                            cardCollection.AddCard(card);

                            completeDeck = deck;
                        }
                        else if (deck.getDeckId() != 0)
                        {
                            deckCollection.AddDeck(completeDeck);
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return deckCollection;
    }

    public Deck GetDeckById(int deckId)
    {
        return null;
    }
}
