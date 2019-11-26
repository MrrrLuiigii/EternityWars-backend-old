package com.eternitywars.Resource;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserContainerResource
{
    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping("/add/{user}")
    public boolean AddUser(@PathVariable("user") User user)
    {
        UserContainerLogic userContainerLogic = new UserContainerLogic();
        return userContainerLogic.AddUser(user);
    }

    @RequestMapping("/login/{userId}")
    public User LoginById(@PathVariable("userId")int userId)
    {
        UserContainerLogic userContainerLogic = new UserContainerLogic();
        return userContainerLogic.Login(userId);
    }

    @RequestMapping("/users")
    public List<User> GetUsers()
    {
        UserContainerLogic userContainerLogic = new UserContainerLogic();
        return userContainerLogic.GetUsers();
    }

    @RequestMapping("/delete/{userId}")
    public boolean DeleteUserById(@PathVariable("userId") int userId)
    {
        UserContainerLogic userContainerLogic = new UserContainerLogic();
        return userContainerLogic.DeleteUserById(userId);
    }

    @RequestMapping("/get/{userId}")
    public User GetUserById(@PathVariable("userId")int userId)
    {
        return restTemplate.getForObject("http://eternity-wars-api/user/get/" + userId, User.class);
    }
}
