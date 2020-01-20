package com.eternitywars.Logic.User;

import com.eternitywars.Models.Enums.AccountStatus;
import com.eternitywars.Models.User;
import com.eternitywars.Models.UserCollection;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class UserContainerLogic
{
    private RestTemplate restTemplate = new RestTemplate();

    public UserCollection GetUsers(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<UserCollection> response = restTemplate.exchange("http://localhost:8083/api/private/user/get" , HttpMethod.GET, request, UserCollection.class);

        return response.getBody();
    }


    public User AddUserByUsernameAndEmail(User user)
    {
        //todo add token in headers
        String token = "";

        UserCollection userCollection = GetUserCollectionFromAPI(token);

        if (CheckUserTaken(userCollection, user))
        {
            return null;
        }

        return AddUser(user, token);
    }

    public User AddUser(User user, String token)
    {
        if(CheckUserTaken(GetUsers(token), user))
        {
            HttpHeaders headers = new HttpHeaders();
            headers.setBearerAuth(token);
            headers.setContentType(MediaType.APPLICATION_JSON);
            JSONObject userJson = new JSONObject(user);
            HttpEntity<String> request = new HttpEntity<>(userJson.toString(), headers);

            ResponseEntity<User> response = restTemplate.exchange("http://localhost:8083/api/private/user/add" , HttpMethod.POST, request, User.class);
            return response.getBody();
        }
        return null;
    }

    private UserCollection GetUserCollectionFromAPI(String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(headers);

        return restTemplate.postForObject("http://localhost:8083/api/private/user/get", request, UserCollection.class);
    }

    public User GetUserByEmail(JSONObject json)
    {
        String email = json.getString("Content");
        String token = json.getString("Token");
        System.out.println(token);

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8083/api/private/user/getByEmail/{email}", HttpMethod.GET, request , User.class, email);

        return response.getBody();

    }

    public User GetUserById(User user)
    {

        //String token = json.getString("Token");
        //System.out.println(token);
        JSONObject output = new JSONObject(user);

        HttpHeaders headers = new HttpHeaders();
        //headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(output.toString(), headers);

        ResponseEntity<String> response = restTemplate.exchange("localhost:8083/api/private/user/getByEmail/", HttpMethod.GET, request, String.class);
        JSONObject api_output = new JSONObject(response.getBody());

        user.setUserId(api_output.getInt("userId"));
        user.setEmail(api_output.getString("email"));
        user.setGold(api_output.getInt("gold"));
        user.setPackAmount(api_output.getInt("packAmount"));
        user.setUsername(api_output.getString("username"));

        if(api_output.getString("accountStatus").equals("online"))
        {
            user.setAccountStatus(AccountStatus.Online);
        }
        else if(api_output.getString("accountStatus").equals("offline"))
        {
            user.setAccountStatus(AccountStatus.Offline);
        }
        else if(api_output.getString("accountStatus").equals("inGame"))
        {
            user.setAccountStatus(AccountStatus.InGame);
        }
        else if(api_output.getString("accountStatus").equals("inLobby"))
        {
            user.setAccountStatus(AccountStatus.InLobby);
        }
        return user;
    }



    private boolean CheckUserTaken(UserCollection userCollection, User user)
    {
        for (User u : userCollection.getUsers())
        {
            if (u.getUsername().equals(user.getUsername()) || u.getEmail().equals(user.getEmail()))
            {
                return false;
            }
        }
        return true;
    }

    public User getUserByUsername(String friendname, String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> request = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange("http://localhost:8083/api/private/user/getByUsername/{friendname}", HttpMethod.GET, request , User.class, friendname);

        return response.getBody();

    }
}
