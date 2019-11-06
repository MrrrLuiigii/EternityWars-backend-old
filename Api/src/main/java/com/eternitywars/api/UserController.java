package com.eternitywars.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/User")
public class UserController
{
    @RequestMapping("/get/{userId}")
    public User GetUserById(@RequestParam("userId")int userId)
    {
        return new User();
    }
}
