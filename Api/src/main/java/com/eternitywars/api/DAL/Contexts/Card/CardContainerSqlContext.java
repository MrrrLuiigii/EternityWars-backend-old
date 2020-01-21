package com.eternitywars.api.DAL.Contexts.Card;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Database.IDatabaseConnection;
import com.eternitywars.api.Database.TestDatabaseConnection;
import com.eternitywars.api.Interfaces.Card.ICardContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.User;

import java.sql.*;

public class CardContainerSqlContext implements ICardContainerContext
{
    private IDatabaseConnection dbc;

    public CardContainerSqlContext(TestDatabaseConnection dbc)
    {
        this.dbc = dbc;
    }

    public CardContainerSqlContext(DatabaseConnection dbc) {
        this.dbc = dbc;
    }


    public CardCollection GetCardsByUser(int userId)
    {
        CardCollection cardCollection = new CardCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "SELECT `id`, `name`, `attack`, `health`, `blue_mana`, `death_essence`, `taunt` " +
                    "FROM `card` AS c " +
                    "INNER JOIN `card_collection` AS cc " +
                    "ON c.id = cc.card_id " +
                    "WHERE cc.user_id = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, userId);

                try (ResultSet rs = pst.executeQuery())
                {
                    while (rs.next())
                    {
                        Card card = FillCard(rs);
                        cardCollection.getCards().add(card);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return cardCollection;
    }

    public Card GetCardById(int cardId)
    {
        Card card = new Card();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `name`, `attack`, `health`, `blue_mana`, `death_essence`, `taunt` " +
                    "from card " +
                    "where `id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, cardId);
                try (ResultSet rs = pst.executeQuery())
                {
                    while(rs.next())
                    {
                        card = FillCard(rs);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return card;
    }

    public CardCollection GetCards()
    {
        CardCollection cardCollection = new CardCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `name`, `attack`, `health`, `blue_mana`, `death_essence`, `taunt` from card;";

            try (Statement st = conn.createStatement())
            {
                try (ResultSet rs = st.executeQuery(query))
                {
                    while (rs.next())
                    {
                        Card card = FillCard(rs);
                        cardCollection.getCards().add(card);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return cardCollection;
    }

    public boolean AddCard(User user, Card card)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call AddCardToCollection(?, ?)};";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, user.getUserId());
                cst.setInt(2, card.getCardId());
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

    public boolean DeleteCard(User user, Card card)
    {
        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "delete from `card_collection` where `user_id` = ? and `card_id` = ?;";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getUserId());
                pst.setInt(2, card.getCardId());
                pst.executeUpdate();
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
            return false;
        }

        return true;
    }

    private Card FillCard(ResultSet rs) throws SQLException
    {
        Card card = new Card();

        card.setCardId(rs.getInt("id"));
        card.setName(rs.getString("name"));
        card.setAttack(rs.getInt("attack"));
        card.setHealth(rs.getInt("health"));
        card.setBlue_mana(rs.getInt("blue_mana"));
        card.setDeath_essence(rs.getInt("death_essence"));
        card.setTaunt(rs.getBoolean("taunt"));

        return card;
    }
}
