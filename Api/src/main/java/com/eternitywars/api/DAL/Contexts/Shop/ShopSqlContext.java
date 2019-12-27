package com.eternitywars.api.DAL.Contexts.Shop;

import com.eternitywars.api.DAL.Repositories.User.UserRepository;
import com.eternitywars.api.Database.DatabaseConnection;

import com.eternitywars.api.Interfaces.Shop.IShopContext;
import com.eternitywars.api.Models.Pack;
import com.eternitywars.api.Models.User;

public class ShopSqlContext implements IShopContext
{
    private DatabaseConnection dbc = new DatabaseConnection();
    UserRepository userRepository = new UserRepository();

    public ShopSqlContext(DatabaseConnection dbc)
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
