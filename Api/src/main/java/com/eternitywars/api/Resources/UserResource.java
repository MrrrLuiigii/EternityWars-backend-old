package com.eternitywars.api.Resources;

import com.eternitywars.api.DAL.Repositories.User.UserRepository;
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

    @RequestMapping(value = "/packamount/{userId}" , method = RequestMethod.GET)
    public int GetPackAmount(@PathVariable("userId") int userId){
        return userRepository.GetPackAmount(userId);
    }

    @PostMapping(value = "/updatepackamount", consumes = "application/json", produces = "application/json")
    public void UpdatePackAmount(@RequestBody User user){ userRepository.UpdatePackAmount(user);}

    @PostMapping(value = "/updategold", consumes = "application/json", produces = "application/json")
    public void UpdateGold(@RequestBody User user){ userRepository.UpdateGold(user);}
}
