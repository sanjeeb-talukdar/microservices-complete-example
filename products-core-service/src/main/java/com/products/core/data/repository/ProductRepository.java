package com.products.core.data.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import com.products.core.data.Catalogue;
import com.products.core.data.Product;

@RepositoryRestResource(collectionResourceRel = "product", path = "product")
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
	@RestResource(path = "catalogue", rel = "catalogue")
	List<Product> findByCatalogue(@Param("name") Catalogue catalogue);
	@RestResource(path = "name", rel = "name")
	List<Product> findByNameContainsIgnoreCase(@Param("name") String name);
	@RestResource(path = "tags", rel = "tags")
	List<Product> findByTagsContainsIgnoreCase(@Param("tags") String tags);
	@RestResource(path = "description", rel = "description")
	List<Product> findByDescriptionContainsIgnoreCase(@Param("description") String description);
}
