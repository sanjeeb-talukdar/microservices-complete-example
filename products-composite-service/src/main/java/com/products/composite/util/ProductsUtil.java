package com.products.composite.util;

import java.util.HashSet;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.products.composite.resource.Price;
import com.products.composite.resource.Product;
import com.products.composite.service.PricingService;

public class ProductsUtil {
	public static void attachPrice(List<Product> products, PricingService pricingService) {
		for (Product product : products) {
			attachPrice(product, pricingService);
		}
	}

	public static void attachPrice(Product product, PricingService pricingService) {
		if (product != null && product.getId() > 0) {
			ResponseEntity<List<Price>> prices = pricingService.get(product.getId());
			if (prices != null && HttpStatus.OK.equals(prices.getStatusCode())) {
				List<Price> price = prices.getBody();
				product.setPrices(new HashSet<Price>(price));
			}
		}
	}
}
