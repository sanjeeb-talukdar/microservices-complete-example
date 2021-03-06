package com.products.composite.util;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.products.composite.resource.Price;
import com.products.composite.resource.Product;
import com.products.composite.service.PricingService;

public class PricingUtil {
	private static final Logger _LOGGER = LoggerFactory.getLogger(PricingUtil.class);

	public static void attachPrice(List<Product> products, PricingService pricingService) {
		Collection<Long> productIds = new HashSet<Long>();
		for (Product product : products) {
			productIds.add(product.getId());
		}
		if (!productIds.isEmpty()) {
			try {
				ResponseEntity<List<Price>> pricesResponse = pricingService.findByIds(productIds);
				if (pricesResponse != null && HttpStatus.OK.equals(pricesResponse.getStatusCode())) {
					List<Price> prices = pricesResponse.getBody();
					for (Price price : prices) {
						for (Product product : products) {
							if (product.getId().equals(price.getProductId())) {
								product.getPrices().add(price);
							}
						}
					}
				}
			} catch (Exception e) {
				_LOGGER.error("Error retrieveing prices .", e);
			}
		}
	}

	public static void attachPrice(Product product, PricingService pricingService) {
		if (product != null && product.getId() > 0) {
			try {
				ResponseEntity<List<Price>> prices = pricingService.getPrices(product.getId());
				if (prices != null && HttpStatus.OK.equals(prices.getStatusCode())) {
					List<Price> price = prices.getBody();
					product.setPrices(new HashSet<Price>(price));
				}
			} catch (Exception e) {
				_LOGGER.error("Error retrieveing price .", e);
			}
		}
	}
}
