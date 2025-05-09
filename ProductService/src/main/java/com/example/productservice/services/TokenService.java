package com.example.productservice.services;

import com.example.productservice.dtos.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TokenService {
    private RestTemplate restTemplate;

    public TokenService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    public boolean validateToken(String token){
        //String url = "http://localhost:8080/users/validate/?token=" + token;
        UserDto userDto= restTemplate
                .getForObject("http://localhost:8080/users/validate/" + token,
                        UserDto.class);
        return userDto != null
                && !userDto.getName().isEmpty()
                && !userDto.getEmail().isEmpty();
    }
}
