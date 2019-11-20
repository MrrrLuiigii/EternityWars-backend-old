package com.eternitywars.Logic.Shop;

import com.eternitywars.Models.Account;
import com.eternitywars.Models.Pack;
import com.eternitywars.Models.User;

public class ShopLogic {
    private CardPickerLogic cpl;

    public ShopLogic() {
        cpl = new CardPickerLogic();
    }

    public boolean BuyPack(User user) {
        if (user.getGold() >= 100) {
            user.setPacksAmount(user.getPacksAmount() + 1);
            user.setGold(user.getGold() - 100);
            return true;
        }

        return false;
    }

    public Pack OpenPack(User user) {
        if (user.getPacksAmount() > 0) {
            Pack pack = cpl.PickCards();
            return pack;
        }

        return null;
    }
}
