package com.eternitywars.api.Controllers;

import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/User")
public class UserController
{
    @RequestMapping("/get/{userId}")
    public User GetUserById(@RequestParam("userId")int userId)
    {
        return null;
    }
}
