package com.eternitywars.api.DAL.Contexts;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.ICardContainerContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class CardContainerContext implements ICardContainerContext
{
    private DatabaseConnection dbc;

    public CardContainerContext(){ dbc = new DatabaseConnection(); }

    public CardCollection GetCards()
    {
        CardCollection cc = new CardCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call GetCards()}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        Card card = new Card();
                        card.setCardId(rs.getInt("id"));
                        card.setName(rs.getString("name"));
                        card.setAttack(rs.getInt("attack"));
                        card.setHealth(rs.getInt("health"));
                        card.setBlue_mana(rs.getInt("blue_mana"));
                        card.setDeath_essence(rs.getInt("death_essence"));
                        cc.getCards().add(card);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return cc;
    }

    public CardCollection GetCardsByUserId(int userId)
    {
        CardCollection cc = new CardCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call GetCardsByUserId(?)}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, userId);
                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        Card card = new Card();
                        card.setCardId(rs.getInt("id"));
                        card.setName(rs.getString("name"));
                        card.setAttack(rs.getInt("attack"));
                        card.setHealth(rs.getInt("health"));
                        card.setBlue_mana(rs.getInt("blue_mana"));
                        card.setDeath_essence(rs.getInt("death_essence"));
                        cc.getCards().add(card);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println(e);
        }

        return cc;
    }

    public Card GetCardById(int cardId)
    {
        Card card = new Card();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "{call GetCardById(?)}";

            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, cardId);
                try (ResultSet rs = cst.executeQuery())
                {
                    while(rs.next())
                    {
                        card.setCardId(rs.getInt("id"));
                        card.setName(rs.getString("name"));
                        card.setAttack(rs.getInt("attack"));
                        card.setHealth(rs.getInt("health"));
                        card.setBlue_mana(rs.getInt("blue_mana"));
                        card.setDeath_essence(rs.getInt("death_essence"));
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
}
