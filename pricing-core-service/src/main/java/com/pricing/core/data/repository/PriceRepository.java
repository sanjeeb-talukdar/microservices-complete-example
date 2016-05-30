package com.pricing.core.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pricing.core.data.Price;
import com.pricing.core.data.PriceId;

@RepositoryRestResource(exported = false)
public interface PriceRepository extends JpaRepository<Price, PriceId> {
	List<Price> findByProductId(@Param ("productId") Long productId);
}
