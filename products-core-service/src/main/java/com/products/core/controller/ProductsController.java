package com.products.core.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.core.service.ProductsService;
import com.products.json.Product;

@RestController
public class ProductsController {

	@Autowired
	private ProductsService paymentsService;

	@RequestMapping(value = "/products", method = RequestMethod.PUT, consumes = "application/json", produces = "application/json", headers = "Accept=application/json")
	public Product create(Product product) {
		return paymentsService.create(product);
	}

	@RequestMapping(value = "/products/{productNumber}", method = RequestMethod.DELETE, produces = "text/plain", headers = "Accept=application/json")
	public String delete(@PathVariable("productNumber") Long productId) {
		return paymentsService.delete(productId);
	}

	@RequestMapping(value="/products/{productNumber}", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public Product findById(@PathVariable("productNumber") Long productId) {
		return paymentsService.findById(productId);
	}
	
	@RequestMapping(value="/products/{productNumber}/details", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public Product findDetailsById(@PathVariable("productNumber") Long productId) {
		return paymentsService.findById(productId);
	}

	@RequestMapping(value="/products", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public Collection<Product> findByProductType(@RequestParam ("productTypeName") String productTypeName) {
		return paymentsService.findByProductType(productTypeName);
	}

}
