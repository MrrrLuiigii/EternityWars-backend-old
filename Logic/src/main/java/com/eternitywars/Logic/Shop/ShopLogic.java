package com.eternitywars.Logic.Shop;

import com.eternitywars.Models.Pack;
import com.eternitywars.Models.User;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class ShopLogic {
    private CardPickerLogic cpl;
    private RestTemplate restTemplate = new RestTemplate();

    public ShopLogic() {
        cpl = new CardPickerLogic();
    }

    public boolean BuyPack(User user) {
        if (user.getGold() >= 100) {
            user.setPackAmount(user.getPackAmount() + 1);
            user.setGold(user.getGold() - 100);

            HttpHeaders headers = new HttpHeaders();
            //headers.setBearerAuth(token);
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject userJson = new JSONObject(user);
            HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
            restTemplate.postForObject("http://eternity-wars-api/api/public/user/updateGold", request, User.class);


            return true;
        }
        return false;
    }

    public Pack OpenPack(User user) {
        if (user.getPackAmount() > 0) {
            Pack pack = cpl.PickCards();
            return pack;
        }
        return null;
    }
}
