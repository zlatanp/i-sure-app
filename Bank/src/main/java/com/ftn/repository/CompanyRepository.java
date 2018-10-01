package com.ftn.repository;

import com.ftn.model.database.Company;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.Optional;

/**
 * Created by Jasmina on 27/06/2017.
 */
public interface CompanyRepository extends JpaRepository<Company, Long> {

    Optional<Company> findByAccountNumber(String accountNumber);
}
