package com.products.core.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.products.core.data.Catalogue;

@RepositoryRestResource(exported = false)
public interface CatalogueRepository extends JpaRepository<Catalogue, Integer> {
	List<Catalogue> findByNameContainsIgnoreCase(@Param("name") String name);
}
