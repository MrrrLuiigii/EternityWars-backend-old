package com.eternitywars.Resource;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

public class UserContainerResource {
    @Autowired
    private RestTemplate restTemplate;

    public boolean AddUser(User user) {
        return userContainerLogic.AddUser(user);
    }

    public User LoginById(int userId) {
        return userContainerLogic.Login(userId);
    }

    public List<User> GetUsers() {
        return userContainerLogic.GetUsers();
    }

    public boolean DeleteUserById(int userId) {
        return userContainerLogic.DeleteUserById(userId);
    }

    public User GetUserById(int userId) {
        return restTemplate.getForObject("http://eternity-wars-api/user/get/" + userId, User.class);
    }
}
