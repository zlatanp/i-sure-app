package com.ftn.service;

import com.ftn.model.dto.UserDTO;

import java.util.List;
import java.util.Optional;

/**
 * Created by Jasmina on 16/11/2017.
 */
public interface UserService {

    List<UserDTO> readAll();

    UserDTO readById(Long id);

    UserDTO create(UserDTO userDTO);

    UserDTO update(Long id, UserDTO companyDTO);

    void delete(Long id);
}
