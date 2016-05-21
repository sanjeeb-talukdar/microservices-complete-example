package com.products.composite.service;

import java.util.List;

import com.products.json.Price;
import com.products.json.Product;

public interface ProductsCompositeService {
	
	void create (Integer categortId, String poductName, Double price);
	
	List<Product> read(String producType);
	
	Product readProduct(Long producId);
	
	String delete(Integer productTypeId, Long productId);
	
	Price readPrice(Long productId);
}
