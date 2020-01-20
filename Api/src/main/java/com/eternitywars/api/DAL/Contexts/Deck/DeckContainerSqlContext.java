package com.eternitywars.api.DAL.Contexts.Deck;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.IDatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;
import com.eternitywars.api.Factories.Deck.DeckFactory;
import com.eternitywars.api.Interfaces.Deck.IDeckContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DeckContainerSqlContext implements IDeckContainerContext
{
    private IDatabaseConnection dbc;

    public DeckContainerSqlContext(DatabaseConnection dbc)
    {
        this.dbc = dbc;
    }

    public DeckContainerSqlContext(TestDatabaseConnection dbc)
    {
        this.dbc = dbc;
    }


    public Deck AddDeck(Deck deck)
    {
        Deck addedDeck = AddEmptyDeck(deck);

        DeckSqlContext deckSqlContext = new DeckSqlContext();

        if (deck.getCards() != null)
        {
            for (Card c : deck.getCards().getCards())
            {
                deckSqlContext.AddCard(addedDeck, c);
            }
        }

        return addedDeck;
    }

    private Deck AddEmptyDeck(Deck deck)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call AddDeck(?, ?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, deck.getUserId());
                cst.setString(2, deck.getName());

                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        deck.setDeckId(rs.getInt("id"));
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return deck;
    }

    public boolean DeleteDeck(Deck deck)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call DeleteDeck(?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, deck.getDeckId());
                cst.executeQuery();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;
    }

    public DeckCollection GetEmptyDecksByUserId(int userId)
    {
        DeckCollection deckCollection = new DeckCollection();

        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `user_id`, `name` from `deck` where `user_id` = ?;";

            try(PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, userId);

                try(ResultSet rs = pst.executeQuery())
                {
                    while(rs.next())
                    {
                        Deck deck = new Deck();
                        deck.setDeckId(rs.getInt("id"));
                        deck.setName(rs.getString("name"));
                        deck.setUserId(rs.getInt("user_id"));
                        deckCollection.AddDeck(deck);
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

    public Deck GetEmptyDeckById(int deckId)
    {
        Deck deck = new Deck();

        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `user_id`, `name` from `deck` where `id` = ?;";

            try(PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, deckId);

                try(ResultSet rs = pst.executeQuery())
                {
                    while(rs.next())
                    {
                        deck.setDeckId(rs.getInt("id"));
                        deck.setName(rs.getString("name"));
                        deck.setUserId(rs.getInt("user_id"));
                    }
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return deck;
    }

    public DeckCollection GetDecksByUserId(int userId)
    {
        DeckCollection deckCollection = new DeckCollection();

        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "SELECT " +
                        "d.`id` AS deck_id, d.`name` AS deck_name, d.`user_id`, " +
                        "c.`id` AS card_id, c.`name` AS card_name, c.`health`, c.`attack`, c.`blue_mana`, c.`death_essence`, c.`taunt` " +
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
                    Deck deck = new Deck(0);
                    Deck completeDeck = null;
                    CardCollection cardCollection = new CardCollection();

                    while(rs.next())
                    {
                        if (rs.getInt("deck_id") != deck.getDeckId())
                        {
                            if (completeDeck != null)
                            {
                                completeDeck.setCards(cardCollection);
                                deckCollection.AddDeck(completeDeck);
                                completeDeck = null;
                            }

                            deck = new Deck();
                            deck.setDeckId(rs.getInt("deck_id"));
                            deck.setName(rs.getString("deck_name"));
                            deck.setUserId(rs.getInt("user_id"));
                            cardCollection = new CardCollection();
                        }

                        if (rs.getInt("deck_id") == deck.getDeckId())
                        {
                            Card card = new Card();
                            card.setCardId(rs.getInt("card_id"));
                            card.setName(rs.getString("card_name"));
                            card.setHealth(rs.getInt("health"));
                            card.setAttack(rs.getInt("attack"));
                            card.setBlue_mana(rs.getInt("blue_mana"));
                            card.setDeath_essence(rs.getInt("death_essence"));
                            card.setTaunt(rs.getBoolean("taunt"));
                            cardCollection.AddCard(card);

                            completeDeck = deck;
                        }
                    }

                    if (deckCollection.getDecks().isEmpty() && completeDeck != null)
                    {
                        completeDeck.setCards(cardCollection);
                        deckCollection.AddDeck(completeDeck);
                    }

                    for(Deck d : deckCollection.getDecks())
                    {
                        if (d.getDeckId() != deck.getDeckId() && completeDeck != null)
                        {
                            completeDeck.setCards(cardCollection);
                            deckCollection.AddDeck(deck);
                            break;
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
        Deck deck = new Deck();

        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "SELECT " +
                    "d.`id` AS deck_id, d.`name` AS deck_name, d.`user_id`, " +
                    "c.`id` AS card_id, c.`name` AS card_name, c.`health`, c.`attack`, c.`blue_mana`, c.`death_essence`, c.`taunt` " +
                    "FROM `deck` AS d " +
                    "INNER JOIN `deck_card` AS dc " +
                    "ON d.`id` = dc.`deck_id` " +
                    "INNER JOIN `card` AS c " +
                    "ON dc.`card_id` = c.`id` " +
                    "WHERE d.`id` = ?;";

            try(PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, deckId);

                try(ResultSet rs = pst.executeQuery())
                {
                    deck.setDeckId(0);
                    CardCollection cardCollection = new CardCollection();

                    while(rs.next())
                    {
                        if (deck.getDeckId() == 0)
                        {
                            deck.setDeckId(rs.getInt("deck_id"));
                            deck.setName(rs.getString("deck_name"));
                            deck.setUserId(rs.getInt("user_id"));
                            cardCollection = new CardCollection();
                        }

                        if (rs.getInt("deck_id") == deck.getDeckId())
                        {
                            Card card = new Card();
                            card.setCardId(rs.getInt("card_id"));
                            card.setName(rs.getString("card_name"));
                            card.setHealth(rs.getInt("health"));
                            card.setAttack(rs.getInt("attack"));
                            card.setBlue_mana(rs.getInt("blue_mana"));
                            card.setDeath_essence(rs.getInt("death_essence"));
                            card.setTaunt(rs.getBoolean("taunt"));
                            cardCollection.AddCard(card);
                        }
                    }

                    deck.setCards(cardCollection);
                }
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return deck;
    }
}
