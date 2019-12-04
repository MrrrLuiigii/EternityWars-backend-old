package com.eternitywars.api.DAL.Contexts.Deck;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.Deck.IDeckContainerContext;
import com.eternitywars.api.Models.Deck;
import com.eternitywars.api.Models.DeckCollection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DeckContainerContext implements IDeckContainerContext
{
    private DatabaseConnection dbc;

    public DeckContainerContext()
    {
        this.dbc = new DatabaseConnection();
    }

    @Override
    public DeckCollection GetAllDeckById(int userId)
    {
        DeckCollection deckCollection = new DeckCollection(new ArrayList<>());
        try(Connection conn = dbc.getDatabaseConnection())
        {
            String query = "SELECT * FROM deck WHERE user_id =" + userId;
            try(CallableStatement cst = conn.prepareCall((query)))
            {
                try(ResultSet rs = cst.executeQuery())
                {
                    while(rs.next())
                    {
                        int id = rs.getInt("id");
                        int user_Id = rs.getInt("user_id");
                        String name = rs.getString("name");
                        deckCollection.AddDeck(new Deck(id, user_Id, name));
                    }
                }
            }
        }
        catch(Exception e){

        }
        return deckCollection;
    }

    @Override
    public boolean AddDeck(Deck deck)
    {
        return false;
    }

    @Override
    public boolean SaveDeck(Deck deck)
    {
        return false;
    }

    @Override
    public boolean DeleteDeck(Deck deck)
    {
        return false;
    }
}
