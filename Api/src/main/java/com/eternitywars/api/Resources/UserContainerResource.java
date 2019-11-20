package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.UserContainerRepository;
import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserContainerResource
{
    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public User GetUserById(@PathVariable("userId")int userId)
    {
        UserContainerRepository userContainerRepository = new UserContainerRepository();
        return userContainerRepository.GetUserById(userId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public UserCollection GetUsers()
    {
        UserContainerRepository userContainerRepository = new UserContainerRepository();
        return userContainerRepository.GetUsers();
    }
}
