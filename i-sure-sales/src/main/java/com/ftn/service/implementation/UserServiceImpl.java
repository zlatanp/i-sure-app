package com.ftn.service.implementation;

import com.ftn.model.dto.UserDTO;
import com.ftn.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Jasmina on 16/11/2017.
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${dc.adress}")
    private String URI;

    private RestTemplate restTemplate = new RestTemplate();

    @Override
    public UserDTO getUserById(Long id) {

        ResponseEntity<UserDTO> response = restTemplate.getForEntity(URI + id.toString(), UserDTO.class);

        if(response == null) {
            System.out.println("Response is null.");
        }else {
            System.out.println("Response is not null.");
            System.out.println(response.getBody());
        }

        return response.getBody();
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return null;
    }

    @Override
    public UserDTO getUserByUsernameAndPassword(String username, String password) {
        return null;
    }
}
