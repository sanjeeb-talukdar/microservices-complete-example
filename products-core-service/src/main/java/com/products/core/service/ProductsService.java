package com.products.core.service;

import java.util.Collection;

import com.products.json.Product;

public interface ProductsService { 
	Product create(Product product);
	String delete(Long productId);
	Product findById(Long productId);
	Collection<Product> findByProductType(String productTypeName);
	
}
