package com.eternitywars.Logic.User;

import com.eternitywars.Models.Relationship;
import com.eternitywars.Models.User;
import com.eternitywars.Models.UserCollection;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

public class UserContainerLogic
{
    @Autowired
    private RestTemplate restTemplate;

    public User AddUserByUsernameAndEmail(User user)
    {
        //todo add token in headers
        String token = "";

        UserCollection userCollection = GetUserCollectionFromAPI(token);

        if (CheckUserTaken(userCollection, user))
        {
            return null;
        }

        return AddUserByUsernameAndEmailAPI(user, token);
    }

    private User AddUserByUsernameAndEmailAPI(User user, String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        JSONObject userJson = new JSONObject(user);
        HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);

        return restTemplate.postForObject("http://eternity-wars-api/api/private/user/add", request, User.class);
    }

    private UserCollection GetUserCollectionFromAPI(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.postForObject("http://eternity-wars-api/api/private/user/get", request, UserCollection.class);
    }

    public User GetUserByEmail(JSONObject json)
    {
        User user = new User();
        user.setEmail(json.getString("Content"));
        String token = json.getString("Token");
        System.out.println(token);
        JSONObject output = new JSONObject(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        RestTemplate rs = new RestTemplate();
        System.out.println("test");

        return rs.getForObject("localhost:8083/api/private/user/getByEmail/" + user.getEmail(), User.class);
    }

    private boolean CheckUserTaken(UserCollection userCollection, User user)
    {
        for (User u : userCollection.getUsers())
        {
            if (u.getUsername() == user.getUsername() || u.getEmail() == user.getEmail())
            {
                return true;
            }
        }

        return false;
    }

}
