package com.products.composite.controller;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.products.composite.resource.Price;
import com.products.composite.resource.Product;
import com.products.composite.service.PricingService;
import com.products.composite.service.ProductsService;
import com.products.composite.util.ProductsUtil;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductsService productsService;
	@Autowired
	private PricingService pricingService;

	@RequestMapping(value = { "", "/" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getProductsFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Product>> getProducts(
			@RequestParam(name = "catalogue", required = false) String catalogue,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "tag", required = false) String tag,
			@RequestParam(name = "description", required = false) String description, UriComponentsBuilder ucBuilder) {
		ResponseEntity<List<Product>> res = productsService.get(catalogue, name, tag, description);
		if (res != null && HttpStatus.OK.equals(res.getStatusCode())) {
			List<Product> products = res.getBody();
			if (!products.isEmpty()) {
				ProductsUtil.attachPrice(new HashSet<Product>(products), pricingService);
			}
			if (products.isEmpty()) {
				return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<List<Product>> getProductsFallBack(
			@RequestParam(name = "catalogue", required = false) String catalogue,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "tag", required = false) String tag,
			@RequestParam(name = "description", required = false) String description, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "getProductsFallBack");
		return new ResponseEntity<List<Product>>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "createProductFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		return productsService.create(product);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Void> createProductFallBack(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "createProductFallBack");
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{productId}", "/{productId}/" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "findByProductIdFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Product> findByProductId(@PathVariable("productId") long productId,
			UriComponentsBuilder ucBuilder) {
		ResponseEntity<Product> res = productsService.findById(productId);
		if (res != null && HttpStatus.OK.equals(res.getStatusCode())) {
			Product products = res.getBody();
			if (products != null) {
				ProductsUtil.attachPrice(products, pricingService);
			}
			return new ResponseEntity<Product>(products, HttpStatus.OK);
		}
		return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Product> findByProductIdFallBack(@PathVariable("productId") long productId,
			UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "findByProductIdFallBack");
		return new ResponseEntity<Product>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{productId}", "/{productId}/" }, method = RequestMethod.PUT)
	@HystrixCommand(fallbackMethod = "updateProductFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId, @RequestBody Product cat,
			UriComponentsBuilder ucBuilder) {
		return productsService.update(productId, cat);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Product> updateProductFallBack(@PathVariable("productId") long productId,
			@RequestBody Product cat, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "updateProductFallBack");
		return new ResponseEntity<Product>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{productId}", "/{productId}/" }, method = RequestMethod.DELETE)
	@HystrixCommand(fallbackMethod = "deleteProductFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") long productId,
			UriComponentsBuilder ucBuilder) {
		return productsService.delete(productId);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Product> deleteProductFallBack(@PathVariable("productId") long productId,
			UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "deleteProductFallBack");
		return new ResponseEntity<Product>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{productId}/price", "/{productId}/price/" }, method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "createPriceFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createPrice(@PathVariable("productId") long productId, @RequestBody Price price,
			UriComponentsBuilder ucBuilder) {
		ResponseEntity<Product> productResponse = productsService.findById(productId);
		if (HttpStatus.OK.equals(productResponse.getStatusCode())) {
			return pricingService.create(productId, price);
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Void> createPriceFallBack(@PathVariable("catalogueId") long productId,
			@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "createPriceFallBack");
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{productId}/price", "/{productId}/price/" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getPricesFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Price>> getPrices(@PathVariable("productId") long productId,
			UriComponentsBuilder ucBuilder) {
		ResponseEntity<List<Price>> res = pricingService.get(productId);
		if (res != null && HttpStatus.OK.equals(res.getStatusCode())) {
			List<Price> products = res.getBody();
			return new ResponseEntity<List<Price>>(products, HttpStatus.OK);
		}
		return new ResponseEntity<List<Price>>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<List<Price>> getPricesFallBack(@PathVariable("productId") long productId,
			UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "getPricesFallBack");
		return new ResponseEntity<List<Price>>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{productId}/price/{currency}",
			"/{productId}/price/{currency}/" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getPriceFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "500000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Price> getPrice(@PathVariable("productId") long productId,
			@PathVariable("currency") String currency, UriComponentsBuilder ucBuilder) {
		if (currency != null && !"".equals(currency.trim())) {
			ResponseEntity<Price> res = pricingService.get(productId, currency.trim().toUpperCase());
			if (res != null && HttpStatus.OK.equals(res.getStatusCode())) {
				Price products = res.getBody();
				return new ResponseEntity<Price>(products, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Price> getPriceFallBack(@PathVariable("productId") long productId,
			@PathVariable("currency") String currency, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "getPriceFallBack");
		return new ResponseEntity<Price>(headers, HttpStatus.ACCEPTED);
	}

}
