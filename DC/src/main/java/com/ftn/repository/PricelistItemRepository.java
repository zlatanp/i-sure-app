package com.ftn.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ftn.model.PricelistItem;

public interface PricelistItemRepository extends JpaRepository<PricelistItem, Long>{
	Optional<PricelistItem> findById(Long id);
}
