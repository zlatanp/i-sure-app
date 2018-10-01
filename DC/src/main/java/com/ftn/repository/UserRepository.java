package com.ftn.repository;

import com.ftn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 16/11/2017.
 */
public interface UserRepository extends JpaRepository<User, Long>{

    Optional<User> findById(Long id);

    Optional<User> findByUsername(String username);
}
