package com.ftn.service.implementation;

import com.ftn.exception.BadRequestException;
import com.ftn.exception.NotFoundException;
import com.ftn.model.User;
import com.ftn.model.dto.UserDTO;
import com.ftn.repository.UserRepository;
import com.ftn.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by Jasmina on 16/11/2017.
 */
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> readAll() {
        return userRepository.findAll().stream().map(UserDTO::new).collect(Collectors.toList());
    }

    @Override
    public UserDTO readById(Long id) {
        final User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        return new UserDTO(user);
    }

    @Override
    public UserDTO create(UserDTO userDTO) {
        final User user = userDTO.construct();
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Override
    public UserDTO update(Long id, UserDTO userDTO) {
        final User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        user.merge(userDTO);
        userRepository.save(user);
        return new UserDTO(user);
    }

    @Override
    public void delete(Long id) {
        final User user = userRepository.findById(id).orElseThrow(NotFoundException::new);
        userRepository.delete(user);
    }
}
