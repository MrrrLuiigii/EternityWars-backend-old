package com.eternitywars.Logic.Account;

import com.eternitywars.Models.Enums.AccountStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

public class UserLogic
{
    @Autowired
    private RestTemplate restTemplate;
    public void Logout()
    {
        //todo logout stuff here
    }

    public boolean ChangeUsername(String username)
    {
        //todo change user name stuff here
        return true;
    }

    public void ChangeStatus(AccountStatus status)
    {
        //todo change status stuff here
    }
}
