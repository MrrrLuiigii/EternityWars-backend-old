package com.eternitywars.Logic.Shop;

import com.eternitywars.Logic.User.UserLogic;
import com.eternitywars.Models.Pack;
import com.eternitywars.Models.User;

public class ShopLogic {

    private CardPickerLogic cardPickerLogic;
    private UserLogic userLogic;

    public ShopLogic() {
        cardPickerLogic = new CardPickerLogic();
        userLogic = new UserLogic();
    }

    public boolean BuyPack(User user) {
        if (user.getGold() >= 100) {
            user.setPackAmount(user.getPackAmount() + 1);
            user.setGold(user.getGold() - 100);
            userLogic.UpdateUser(user);
            return true;
        }

        return false;
    }

    public Pack OpenPack(User user) {
        if (user.getPackAmount() > 0) {
            Pack pack = cardPickerLogic.PickCards();
            return pack;
        }

        return null;
    }
}
