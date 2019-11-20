package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.UserContainerRepository;
import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserResource
{
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public User GetUserById(@PathVariable("userId")int userId)
    {
        UserContainerRepository userContainerRepository = new UserContainerRepository();
        return userContainerRepository.GetUserById(userId);
    }
}
