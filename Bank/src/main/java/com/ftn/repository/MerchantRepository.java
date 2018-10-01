package com.ftn.repository;

import com.ftn.model.database.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by Jasmina on 10/12/2017.
 */
public interface MerchantRepository extends JpaRepository<Merchant, Long>{

    Merchant findByMerchantIdAndPassword(String id, String password);
}
