package com.eternitywars.Resource;

import com.eternitywars.Logic.User.UserContainerLogic;
import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.User;
import com.eternitywars.Models.UserCollection;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping(value = "/user", method = RequestMethod.GET)
public class UserContainerResource {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public User AddUser(/*User user*/)
    {
        //create Mock user
        //we get real user from front end via socket
        User user = new User();
        user.setId(1);
        user.setGoogleId("asuhqpiufashkv");
        user.setUsername("yeet");
        user.setEmail("yeet");
        user.setGold(999);
        user.setPackAmount(999);
        user.setAccountStatus(AccountStatus.Online);
        user.setCardCollection(null);
        user.setDeckCollection(null);
        user.setFriendCollection(null);

        //todo check if user exists and credentils are okay before resttemplate call
        //UserContainerLogic userContainerLogic = new UserContainerLogic();

        //post request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject userJson = new JSONObject(user);
        HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);
        user = restTemplate.postForObject("http://eternity-wars-api/user/add", request, User.class);
        return user;
    }

    public User LoginById(int userId) {
        UserContainerLogic userContainerLogic = new UserContainerLogic();
        return userContainerLogic.Login(userId);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public UserCollection GetUsers() {
        return restTemplate.getForObject("http://eternity-wars-api/user/get", UserCollection.class);
    }

    @RequestMapping(value = "/delete/{userId}", method = RequestMethod.GET)
    public boolean DeleteUserById(@PathVariable("userId")int userId) {
        UserContainerLogic userContainerLogic = new UserContainerLogic();
        return userContainerLogic.DeleteUserById(userId);
    }

    @RequestMapping(value = "/get/{userId}", method = RequestMethod.GET)
    public User GetUserById(@PathVariable("userId")int userId) {
        return restTemplate.getForObject("http://eternity-wars-api/user/get/" + userId, User.class);
    }
}
