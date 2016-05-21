package com.pricing.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pricing.core.service.PricingCoreService;
import com.products.json.Price;

@RestController
public class PricingCoreController {

	@Autowired
	private PricingCoreService accoutService;

	@RequestMapping(value = "/products/{productNumber}/price", method = RequestMethod.PUT, produces = "application/json", headers = "Accept=application/json")
	public Price create(@PathVariable("productNumber") Long productId,
			@RequestParam(value = "price") Double price) {
		return accoutService.create(productId, price);
	}

	@RequestMapping(value = "/products/{productNumber}/price", method = RequestMethod.DELETE, produces = "text/plain", headers = "Accept=application/json")
	public String delete(@PathVariable("productNumber") Long productId) {
		return accoutService.delete(productId);
	}
	
	@RequestMapping(value = "/products/{productNumber}/price", method = RequestMethod.GET, produces = "application/json", headers = "Accept=application/json")
	public Price findById(@PathVariable("productNumber") Long productId) {
		return accoutService.findById(productId);
	}

}
