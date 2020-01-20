package com.eternitywars.api.Resources.User;

import com.eternitywars.api.DAL.Repositories.User.UserContainerRepository;
import com.eternitywars.api.Models.User;
import com.eternitywars.api.Models.UserCollection;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/user")
public class UserContainerResource
{
    private UserContainerRepository userContainerRepository = new UserContainerRepository();

    @PostMapping(value = "/add", consumes = "application/json", produces = "application/json")
    public User AddUser(@RequestBody User user)
    {
        return userContainerRepository.AddUser(user);
    }

    @GetMapping(value = "/getById", consumes = "application/json", produces = "application/json")
    public User GetUserById(@RequestBody String json)
    {
        JSONObject jsonObject = new JSONObject(json);
        int userId = jsonObject.getInt("userId");
        return userContainerRepository.GetUserById(userId);
    }

    @GetMapping(value = "/getByEmail/{email}")
    public User GetUserByEmail(@PathVariable("email") String email)
    {
        return userContainerRepository.GetUserByEmail(email);
    }

    @GetMapping(value = "/getByUsername/{username}")
    public User GetUserByUsername(@PathVariable("username") String username)
    {
        return userContainerRepository.GetUserByUsername(username);
    }

    @GetMapping(value = "/get")
    public UserCollection GetUsers()
    {
        return userContainerRepository.GetUsers();
    }
}
