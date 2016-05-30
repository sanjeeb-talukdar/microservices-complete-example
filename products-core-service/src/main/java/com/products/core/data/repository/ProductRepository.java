package com.products.core.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.products.core.data.Product;

@RepositoryRestResource(exported = false)
public interface ProductRepository extends JpaRepository<Product, Long> {
	List<Product> findByNameContainsIgnoreCase(@Param("name") String name);
	List<Product> findByTagsContainsIgnoreCase(@Param("tags") String tags);
	List<Product> findByDescriptionContainsIgnoreCase(@Param("description") String description);
	@Query(name = "Product.retrieveByCatalogue", value = "select p from Product p inner join p.catalogues c where lower(c.name) like :catalogue ")
	List<Product> retrieveByCatalogue(@Param("catalogue") String catalogue);
}
