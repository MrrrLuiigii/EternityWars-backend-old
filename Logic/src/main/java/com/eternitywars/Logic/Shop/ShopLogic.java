package com.eternitywars.Logic.Shop;

import com.eternitywars.Models.Account;
import com.eternitywars.Models.Pack;

public class ShopLogic {
    private CardPickerLogic cpl;

    public ShopLogic() {
        cpl = new CardPickerLogic();
    }

    public boolean BuyPack(Account account) {
        if (account.getGold() >= 100) {
            account.setPacksAmount(account.getPacksAmount() + 1);
            account.setGold(account.getGold() - 100);
            return true;
        }

        return false;
    }

    public Pack OpenPack(Account account) {
        if (account.getPacksAmount() > 0) {
            Pack pack = cpl.PickCards();
            return pack;
        }

        return null;
    }
}
