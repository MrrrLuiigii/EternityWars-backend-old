package com.eternitywars.api.DAL.Contexts.Card;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Card.ICardContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.User;

import java.sql.*;

public class CardContainerSqlContext implements ICardContainerContext
{
    private DatabaseConnection dbc;

    public CardContainerSqlContext()
    {
        dbc = new DatabaseConnection();
    }



    public CardCollection GetCardsByUser(User user)
    {
        CardCollection cardCollection = new CardCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `name`, `attack`, `health`, `blue_mana`, `death_essence` " +
                    "from card " +
                    "where card.`id` in (" +
                        "select card_id " +
                        "from card_collection " +
                        "where `user_id` = ?" +
                    ");";

            try (PreparedStatement pst = conn.prepareStatement(query))
            {
                pst.setInt(1, user.getUserId());

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
            String query = "select `id`, `name`, `attack`, `health`, `blue_mana`, `death_essence` " +
                    "from card " +
                    "where `id` = ?;";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, cardId);
                try (ResultSet rs = cst.executeQuery())
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

    @Override
    public boolean AddCard(User user, Card card) {
        return false;
    }

    @Override
    public boolean DeleteCard(User user, Card card) {
        return false;
    }


    public boolean AddCard()
    {
        return false;
    }

    public boolean DeleteCard()
    {
        return false;
    }

    public CardCollection GetCards()
    {
        CardCollection cardCollection = new CardCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "select `id`, `name`, `attack`, `health`, `blue_mana`, `death_essence` from card;";

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

    @Override
    public CardCollection GetCardsByUser(int userId) {
        return null;
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

        return card;
    }

}
