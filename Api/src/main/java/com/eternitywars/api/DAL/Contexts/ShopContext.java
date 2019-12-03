package com.eternitywars.api.DAL.Contexts;

import com.eternitywars.api.Database.DatabaseConnection;
import com.eternitywars.api.Interfaces.IShopContext;
import com.eternitywars.api.Models.Pack;

public class ShopContext implements IShopContext
{
    private DatabaseConnection dbc = new DatabaseConnection();

    @Override
    public Pack OpenPack(int userId)
    {
        return null;
    }

    @Override
    public void BuyPack(int userId)
    {

    }
}
