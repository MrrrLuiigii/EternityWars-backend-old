package com.eternitywars.api.Resources.User;

import com.eternitywars.api.DAL.Repositories.User.UserRepository;
import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/user", method = RequestMethod.GET)
public class UserResource
{
    private UserRepository userRepository = new UserRepository();

    @PostMapping(value = "/updateUsername", consumes = "application/json", produces = "application/json")
    public User UpdateUsername(@RequestBody User user)
    {
        return userRepository.UpdateUsername(user);
    }

    @PostMapping(value = "/updateUserStatus", consumes = "application/json", produces = "application/json")
    public void UpdateAccountStatus(@RequestBody User user)
    {
        userRepository.UpdateAccountStatus(user);
    }

    @PostMapping(value = "/updatePackAmount", consumes = "application/json", produces = "application/json")
    public void UpdatePackAmount(@RequestBody User user)
    {
        userRepository.UpdatePackAmount(user);
    }
}
