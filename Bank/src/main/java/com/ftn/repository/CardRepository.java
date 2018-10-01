package com.ftn.repository;

import com.ftn.model.database.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 04/12/2017.
 */
public interface CardRepository extends JpaRepository<Card, Long> {

    Optional<Card> findByPan(String pan);

    Optional<Card> findByPanAndSecurityCode(String pan, int securityCode);

}
