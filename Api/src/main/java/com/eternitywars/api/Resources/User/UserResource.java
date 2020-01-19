package com.eternitywars.api.Resources.User;

import com.eternitywars.api.DAL.Repositories.User.UserContainerRepository;
import com.eternitywars.api.DAL.Repositories.User.UserRepository;
import com.eternitywars.api.Models.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/private/user")
public class UserResource
{
    private UserRepository userRepository = new UserRepository();
    private UserContainerRepository userContainerRepository = new UserContainerRepository();

    @PostMapping(value = "/updateUsername", consumes = "application/json", produces = "application/json")
    public boolean UpdateUsername(@RequestBody User user)
    {
        return userRepository.UpdateUsername(user);
    }

    @PostMapping(value = "/updateAccountStatus", consumes = "application/json", produces = "application/json")
    public boolean UpdateAccountStatus(@RequestBody User user)
    {
        return userRepository.UpdateAccountStatus(user);
    }

    @PostMapping(value = "/updatePackAmount", consumes = "application/json", produces = "application/json")
    public boolean UpdatePackAmount(@RequestBody User user)
    {
        return userRepository.UpdatePackAmount(user);
    }

    @PostMapping(value = "/updateGold", consumes = "application/json", produces = "application/json")
    public boolean UpdateGold(@RequestBody User user)
    {
        User updatedUser = userContainerRepository.GetUserById(user.getUserId());
        updatedUser.setGold(updatedUser.getGold() + user.getGold());
        return userRepository.UpdateGold(updatedUser);
    }
}
