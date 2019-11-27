package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Contexts.UserContext;
import com.eternitywars.api.DAL.Repositories.UserRepository;
import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserResource
{
    private UserRepository userRepository = new UserRepository();
    @RequestMapping(value = "/edit/{userId}", method = RequestMethod.GET)
    public void ChangeUsername(@PathVariable("userId")int userId, String username){
        userId = 1;
        username = "Nick";
        userRepository.ChangeUsername(userId, username);
    }
}
