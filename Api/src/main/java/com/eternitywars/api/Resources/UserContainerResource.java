package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.User.UserContainerRepository;
import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/user", method = RequestMethod.GET)
public class UserContainerResource
{
    private UserContainerRepository userContainerRepository = new UserContainerRepository();

    @PostMapping(value = "/getbyemail", consumes = "text/plain", produces = "application/json")
    public User GetUserByEmail(@RequestBody String userEmail)
    {
        return userContainerRepository.GetUserByEmail(userEmail);
    }

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public User addUser(@RequestBody User user)
    {
        return userContainerRepository.AddUser(user);
    }

    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public User GetUserById(@PathVariable("userId")int userId)
    {
        return userContainerRepository.GetUserById(userId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public UserCollection GetUsers()
    {
        return userContainerRepository.GetUsers();
    }
}
