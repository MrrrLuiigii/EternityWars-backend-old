package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.UserRepository;
import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserResource
{
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public User GetUserById(@PathVariable("userId")int userId)
    {
        UserRepository userRepository = new UserRepository();
        return userRepository.GetUserById(userId);
    }
}
