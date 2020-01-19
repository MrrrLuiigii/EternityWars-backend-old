package com.eternitywars.Logic.Card;

import com.eternitywars.Models.CardCollection;
import com.eternitywars.Models.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

public class CardContainerLogic
{
    private RestTemplate restTemplate = new RestTemplate();

    public CardCollection GetCardsByUserId(User user, String token)
    {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(headers);
        ResponseEntity<CardCollection> response = restTemplate.exchange(
                "http://localhost:8083/api/private/card/getByUserId/{userId}",
                HttpMethod.GET, request, CardCollection.class, user.getUserId());
        return response.getBody();
    }
}
