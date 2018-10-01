package com.ftn.service;

import com.ftn.model.dto.UserDTO;

/**
 * Created by Jasmina on 16/11/2017.
 */
public interface UserService {

    UserDTO getUserById(Long id);

    UserDTO getUserByUsername(String username);

    UserDTO getUserByUsernameAndPassword(String username, String password);
}
