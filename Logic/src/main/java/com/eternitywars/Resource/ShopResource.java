package com.eternitywars.Resource;

import com.eternitywars.Logic.Shop.ShopLogic;
import com.eternitywars.Models.User;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/shop", method = RequestMethod.GET)
public class ShopResource
{
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/buy/{userId}", method = RequestMethod.GET)
    public User BuyPack(@PathVariable("userId")int userId)
    {
        User user = new User();
        user.setId(userId);
        user.setGold(300);
        user.setPackAmount(0);

        ShopLogic shopLogic = new ShopLogic();

        if(shopLogic.BuyPack(user))
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject userJson = new JSONObject(user);

            System.out.println(userJson);
            HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
            restTemplate.postForObject("http://eternity-wars-api/user/updatepackamount", request, User.class);
            restTemplate.postForObject("http://eternity-wars-api/user/updategold", request, User.class);
        }

        return user;
    }
}
























