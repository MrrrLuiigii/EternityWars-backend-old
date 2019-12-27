package com.eternitywars.api.Resources.User;

import com.eternitywars.api.DAL.Repositories.User.UserContainerRepository;
import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/user")
public class UserContainerResource
{
    private UserContainerRepository userContainerRepository = new UserContainerRepository();

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public User addUser(@RequestBody User user)
    {
        return userContainerRepository.AddUser(user);
    }

    @GetMapping(value = "/getById/{userId}")
    public User GetUserById(@PathVariable("userId")int userId)
    {
        return userContainerRepository.GetUserById(userId);
    }

    @GetMapping(value = "/getByEmail/{email}")
    public User GetUserByEmail(@PathVariable("email") String email)
    {
        return userContainerRepository.GetUserByEmail(email);
    }

    @GetMapping(value = "/get")
    public UserCollection GetUsers()
    {
        return userContainerRepository.GetUsers();
    }
}