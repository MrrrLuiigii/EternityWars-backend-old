package com.eternitywars.api.Interfaces;

import com.eternitywars.api.Models.Pack;

public interface IShopContext
{
    Pack OpenPack(int userId);
    void BuyPack(int userId);
}
