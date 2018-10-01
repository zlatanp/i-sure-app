package com.ftn.repository;

import com.ftn.model.Customer;
import com.ftn.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 21/11/2017.
 */
public interface CustomerRepository extends JpaRepository<Customer, Long>{

    Optional<Customer> findById(Long id);

    Optional<Customer> findByPersonalId(String personalId);
}
