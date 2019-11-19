package com.eternitywars.Logic.Account;

import com.eternitywars.Models.Account;
import com.eternitywars.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class UserContainerLogic
{
    @Autowired
    private RestTemplate restTemplate;

    List<Account> accounts;

    public boolean AddUser(User user){
        return restTemplate.getForObject("example", boolean.class);
    }

    public User Login(int userId){
        //todo login stuff here(add variable in url)
        User user = restTemplate.getForObject("example",User.class);
        return user;
    }

    public List<User> GetUsers(){
        //todo get accounts stuff here
        List<User> users = restTemplate.getForObject("example", List.class);
        return users;
    }

    public boolean DeleteUserById(int userId){
        return restTemplate.getForObject("example", boolean.class);
    }

    public User GetUserById(int userId)
    {
        //todo (add variable in url)
        User user = restTemplate.getForObject("example", User.class);
        return user;
    }

}
