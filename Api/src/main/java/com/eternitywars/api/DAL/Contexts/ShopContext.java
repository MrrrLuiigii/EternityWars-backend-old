package com.eternitywars.api.DAL.Contexts;

import com.eternitywars.api.DAL.Repositories.UserRepository;
import com.eternitywars.api.Database.DatabaseConnection;

import com.eternitywars.api.Interfaces.IShopContext;
import com.eternitywars.api.Models.Pack;
import com.eternitywars.api.Models.User;

import java.sql.Connection;

public class ShopContext implements IShopContext
{
    private DatabaseConnection dbc = new DatabaseConnection();
    UserRepository userRepository = new UserRepository();

    public ShopContext(DatabaseConnection dbc)
    {
        this.dbc = dbc;
    }

    @Override
    public Pack OpenPack(User user)
    {
        //minus 1 for packs user
        //generate cards
        return null;
    }

    @Override
    public void BuyPack(User user)
    {

    }
}
