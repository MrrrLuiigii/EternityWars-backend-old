package com.eternitywars.Logic.User;

import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.User;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestTemplate;


public class UserLogic
{
    @Autowired
    private RestTemplate restTemplate;

    HttpHeaders headers =  new HttpHeaders();
    private JSONObject jsonObject;

    public boolean ChangeUsername(User user, String token) throws JSONException
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(new JSONObject(user).toString(),headers);

        restTemplate.postForObject("http://localhost:8083/api/public/user/changeUsername", request,User.class);
        return  restTemplate.getForObject("example", boolean.class);
    }

    public boolean ChangeStatus(User user, String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(new JSONObject(user).toString(),headers);

        restTemplate.postForObject("http://localhost:8083/api/public/user/changeStatus", request,User.class);
        return  true;
    }
}
