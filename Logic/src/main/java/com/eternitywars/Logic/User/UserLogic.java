package com.eternitywars.Logic.User;

import com.eternitywars.Models.Enums.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class UserLogic
{
    @Autowired
    private RestTemplate restTemplate;

    public void Logout()
    {
        //todo logout stuff here(Intergrate with Front-end)
    }

    public boolean ChangeUsername(String username)
    {
        //todo fill in url(add parameter)
        return  restTemplate.getForObject("example", boolean.class);
    }

    public boolean ChangeStatus(AccountStatus status)
    {
        //todo fill in url(add parameter)
        return  restTemplate.getForObject("example", boolean.class);
    }
}
