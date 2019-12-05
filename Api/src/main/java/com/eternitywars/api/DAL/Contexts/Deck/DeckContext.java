package com.eternitywars.api.DAL.Contexts.Deck;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Deck.IDeckContext;
import com.eternitywars.api.Models.Card;
import com.eternitywars.api.Models.CardCollection;
import com.eternitywars.api.Models.Deck;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;

public class DeckContext implements IDeckContext
{

    private DatabaseConnection dbc;

    public DeckContext()
    {
        this.dbc = new DatabaseConnection();
    }

    @Override
    public boolean AddCard(Deck deck, Card card)
    {
        return false;
    }

    @Override
    public boolean DeleteCard(Deck deck, Card card)
    {
        return false;
    }

    @Override
    public boolean UpdateDeck(Deck deck)
    {
        return false;
    }

    @Override
    public CardCollection GetCardsInDeck(Deck deck)
    {
        CardCollection cardCollection = new CardCollection();

        try (Connection conn = dbc.getDatabaseConnection())
        {
            String query = "SELECT * FROM card INNER JOIN deck_card ON deck_card.card_id = card.id where deck_card.deck_id =?";
            try (CallableStatement cst = conn.prepareCall(query))
            {
                cst.setInt(1, deck.getDeckId());
                try (ResultSet rs = cst.executeQuery())
                {
                    while (rs.next())
                    {
                        int cardId = rs.getInt("id");
                        String name = rs.getString("name");
                        int health = rs.getInt("health");
                        int attack = rs.getInt("attack");
                        int blue_mana = rs.getInt("blue_mana");
                        int death_essence = rs.getInt("death_essence");

                        cardCollection.AddCard(new Card(cardId,name, health, attack, blue_mana, death_essence));
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
}
