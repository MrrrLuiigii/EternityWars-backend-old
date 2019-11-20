package com.eternitywars.Resource;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Models.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserContainerResource
{
    private UserContainerLogic userContainerLogic = new UserContainerLogic();

    @RequestMapping("/add/{user}")
    public boolean AddUser(@PathVariable("user") User user) {
        return userContainerLogic.AddUser(user);
    }

    @RequestMapping("/login/{userid}")
    public User LoginById(@PathVariable("userid")int userId){
        return userContainerLogic.Login(userId);
    }

    @RequestMapping("/users")
    public List<User> GetUsers(){
        return userContainerLogic.GetUsers();
    }

    @RequestMapping("/delete/{userid}")
    public boolean DeleteUserById(@PathVariable("userid") int userId){
        return userContainerLogic.DeleteUserById(userId);
    }

    @RequestMapping("/get/{userid}")
    public User GetUserById(@PathVariable("userid")int userId){
        return userContainerLogic.GetUserById(userId);
    }




}
