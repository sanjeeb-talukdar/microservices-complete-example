package com.pricing.core.service;

import com.products.json.Price;

public interface PricingCoreService {

	Price create(Long productId, Double price);

	String delete(Long productId);

	Price findById(Long productId);

}
