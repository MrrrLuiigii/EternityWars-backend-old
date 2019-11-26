package com.eternitywars.Logic.User;

import com.eternitywars.Models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.List;

@RestController
public class UserContainerLogic {
    @Autowired
    private RestTemplate restTemplate;

    public boolean AddUser(User user) {
        // todo fill in url(add parameter)
        return restTemplate.getForObject("example", boolean.class);
    }

    public User Login(int userId) {
        // todo fill in url(add parameter)
        return restTemplate.getForObject("example", User.class);
    }

    public List<User> GetUsers() {
        // todo fill in url(add parameter)
        return restTemplate.getForObject("example", List.class);
    }

    public boolean DeleteUserById(int userId) {
        // todo fill in url(add parameter)
        return restTemplate.getForObject("example", boolean.class);
    }

    public User GetUserById(int userId) {
        // todo fill in url(add parameter)
        return restTemplate.getForObject("example", User.class);
    }
}
