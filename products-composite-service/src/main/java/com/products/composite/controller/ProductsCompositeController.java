package com.products.composite.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.products.composite.service.ProductsCompositeService;
import com.products.json.Product;

@RestController
public class ProductsCompositeController {

	@Autowired
	private ProductsCompositeService productService;

	@RequestMapping(value = "/products/{productId}", method = RequestMethod.GET, produces = "text/plain", headers = "Accept=application/json")
	public Product retrieveProduct(@PathVariable("productId") Long productId) {
		// TODO Auto-generated method stub
		return productService.readProduct(productId);
	}

	
}
