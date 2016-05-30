package com.products.composite.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.products.composite.resource.Catalogue;
import com.products.composite.resource.Price;
import com.products.composite.resource.Product;
import com.products.composite.service.CatalogueService;
import com.products.composite.service.PricingService;
import com.products.composite.service.ProductsService;
import com.products.composite.util.ProductsUtil;

@RestController
public class CatalogueProductController {

	@Autowired
	private ProductsService productsService;
	@Autowired
	private CatalogueService catalogueService;
	@Autowired
	private PricingService pricingService;

	@RequestMapping(value = { "/catalogue/{catalogueId}/product",
			"/catalogue/{catalogueId}/product/" }, method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "createProductFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createProduct(@PathVariable("catalogueId") int catalogueId,
			@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		ResponseEntity<Catalogue> response = catalogueService.findById(catalogueId);
		if (HttpStatus.OK.equals(response.getStatusCode())) {
			ResponseEntity<Void> retVal = productsService.create(product);
			String productIdStr = retVal.getHeaders().getFirst("productId");
			if (productIdStr != null && !"".equals(productIdStr.trim())) {
				long productId = Long.valueOf(productIdStr.trim());
				if (productId > -1) {
					/** Associate the product with catalogue */
					catalogueService.attach(catalogueId, productId);
					return retVal;
				}
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Void> createProductFallBack(@PathVariable("catalogueId") int catalogueId,
			@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "createProductFallBack");
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/catalogue/{catalogueId}/product",
			"/catalogue/{catalogueId}/product/" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getProductsFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Product>> getProducts(@PathVariable("catalogueId") int catalogueId,
			UriComponentsBuilder ucBuilder) {
		ResponseEntity<Catalogue> response = catalogueService.findById(catalogueId);
		if (response != null && HttpStatus.OK.equals(response.getStatusCode())) {
			Catalogue catalogue = response.getBody();
			if (catalogue != null && !catalogue.getProducts().isEmpty()) {
				ProductsUtil.attachPrice(catalogue.getProducts(), pricingService);
			}
			return new ResponseEntity<List<Product>>(new ArrayList<Product>(catalogue.getProducts()), HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);

	}
	
	@SuppressWarnings("unused")
	private ResponseEntity<List<Product>> getProductsFallBack(@PathVariable("catalogueId") int catalogueId,
			UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "getProductsFallBack");
		return new ResponseEntity<List<Product>>(headers, HttpStatus.FOUND);
	}

	@RequestMapping(value = { "/catalogue/{catalogueId}/product/{productId}",
			"/catalogue/{catalogueId}/product/{productId}" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getProductFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Product> getProduct(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, UriComponentsBuilder ucBuilder) {
		ResponseEntity<Catalogue> response = catalogueService.findById(catalogueId);
		ResponseEntity<Product> response1 = productsService.findById(productId);
		if (response != null && response1 != null && HttpStatus.OK.equals(response.getStatusCode())
				&& HttpStatus.OK.equals(response1.getStatusCode())) {
			Catalogue catalogue = response.getBody();
			Product product = response1.getBody();
			if (catalogue != null && catalogue.getProducts() != null && catalogue.getProducts().contains(product)) {
				ProductsUtil.attachPrice(product, pricingService);
			}
			return new ResponseEntity<Product>(product, HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Product> getProductFallBack(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "getProductsFallBack");
		return new ResponseEntity<Product>(headers, HttpStatus.FOUND);
	}

	@RequestMapping(value = { "/catalogue/{catalogueId}/product/{productId}/price",
			"/catalogue/{catalogueId}/product/{productId}/price/" }, method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "createPriceFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createPrice(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("catalogueId") long productId, @RequestBody Price price, UriComponentsBuilder ucBuilder) {
		ResponseEntity<Catalogue> productResponse = catalogueService.findById(catalogueId);
		if (HttpStatus.OK.equals(productResponse.getStatusCode())) {
			Catalogue catalogue = productResponse.getBody();
			if (catalogue.getProducts() != null && !catalogue.getProducts().isEmpty()) {
				for (Product product : catalogue.getProducts()) {
					if (product.getId() == productId) {
						/** Associated product found */
						return pricingService.create(productId, price);
					}
				}
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Void> createPriceFallBack(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("catalogueId") long productId, @RequestBody Price price, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "createPriceFallBack");
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/catalogue/{catalogueId}/product/{productId}/price",
			"/catalogue/{catalogueId}/product/{productId}/price/" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getPricesFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Price>> getPrices(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, UriComponentsBuilder ucBuilder) {
		ResponseEntity<List<Price>> res = pricingService.get(productId);
		if (res != null && HttpStatus.OK.equals(res.getStatusCode())) {
			List<Price> products = res.getBody();
			return new ResponseEntity<List<Price>>(products, HttpStatus.OK);
		}
		return new ResponseEntity<List<Price>>(HttpStatus.NOT_FOUND);
	}
	
	@SuppressWarnings("unused")
	private ResponseEntity<List<Price>> getPricesFallBack(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "getPricesFallBack");
		return new ResponseEntity<List<Price>>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/catalogue/{catalogueId}/product/{productId}/price/{currency}",
			"/catalogue/{catalogueId}/product/{productId}/price/{currency}/" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getPriceFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Price> getPrice(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, @PathVariable("currency") String currency, UriComponentsBuilder ucBuilder) {
		if (currency != null && !"".equalsIgnoreCase(currency.trim())) {
			ResponseEntity<Price> price = pricingService.get(productId,currency.trim().toUpperCase());
			if (price == null) {
				return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Price>(price.getBody(), HttpStatus.OK);
		}
		return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
	}
	
	@SuppressWarnings("unused")
	private ResponseEntity<Price> getPriceFallBack(@PathVariable("catalogueId") int catalogueId,
	@PathVariable("productId") long productId, @PathVariable("currency") String currency, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "getPriceFallBack");
		return new ResponseEntity<Price>(headers, HttpStatus.ACCEPTED);
	}
	
}
