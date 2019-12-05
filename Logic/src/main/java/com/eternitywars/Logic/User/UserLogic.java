package com.eternitywars.Logic.User;

import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


public class UserLogic
{
    @Autowired
    private RestTemplate restTemplate;

    HttpHeaders headers =  new HttpHeaders();
    private JSONObject jsonObject;


    public void Logout()
    {
        //todo logout stuff here(Integrate with Front-end)
    }

    public void UpdateUser(User user)
    {
        //todo update user pack amount and gold
    }

    public boolean ChangeUsername(String username) throws JSONException
    {
        //todo fill in url(add parameter)
        headers.setContentType(MediaType.APPLICATION_JSON);
        jsonObject = new JSONObject();
        jsonObject.put("username", username);
        return  restTemplate.getForObject("example", boolean.class);
    }

    public boolean ChangeStatus(AccountStatus status)
    {
        //todo fill in url(add parameter)
        return  restTemplate.getForObject("example", boolean.class);
    }
}
