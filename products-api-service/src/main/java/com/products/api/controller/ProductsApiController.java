package com.products.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.products.api.resource.Catalogue;
import com.products.api.resource.Price;
import com.products.api.resource.Product;
import com.products.api.service.ProductsCompositeService;

@RestController
public class ProductsApiController {
	@Autowired
	private ProductsCompositeService productsCompositeService;
	
	@RequestMapping(value = "/catalogue/", method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Catalogue>> getCatalogues(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "attachPrice", required = false, defaultValue = "true") boolean attachPrice) {
		return productsCompositeService.getCatalogues(name, attachPrice);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/", method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> findByCatalogueId(@PathVariable("catalogueId") int catalogueId) {
		return productsCompositeService.findByCatalogueId(catalogueId);
	}

	@RequestMapping(value = "/catalogue/", method = RequestMethod.POST)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createCatalogue(@RequestBody Catalogue catalogue) {
		return productsCompositeService.createCatalogue(catalogue);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/", method = RequestMethod.PUT)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> updateCatalogue(@PathVariable("catalogueId") int catalogueId,
			@RequestBody Catalogue catalogue) {
		return productsCompositeService.updateCatalogue(catalogueId, catalogue);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/", method = RequestMethod.DELETE)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> deleteCatalogue(@PathVariable("catalogueId") int catalogueId) {
		return productsCompositeService.deleteCatalogue(catalogueId);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/attach/{productId}/", method = RequestMethod.PUT)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> attach2Catalogue(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId) {
		return productsCompositeService.attach2Catalogue(catalogueId, productId);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/detach/{productId}/", method = RequestMethod.PUT)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> detachFromCatalogue(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId) {
		return productsCompositeService.detachFromCatalogue(catalogueId, productId);
	}

	@RequestMapping(value = "/product/", method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Product>> getProducts(
			@RequestParam(name = "catalogue", required = false) String catalogue,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "tag", required = false) String tag,
			@RequestParam(name = "description", required = false) String description) {
		return productsCompositeService.getProducts(catalogue, name, tag, description);
	}

	@RequestMapping(value = "/product/", method = RequestMethod.POST)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createProduct(@RequestBody Product product) {
		return productsCompositeService.createProduct(product);
	}

	@RequestMapping(value = "/product/{productId}/", method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Product> findByProductId(@PathVariable("productId") long productId) {
		return productsCompositeService.findByProductId(productId);
	}

	@RequestMapping(value = "/product/{productId}/", method = RequestMethod.PUT)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId, @RequestBody Product cat) {
		return productsCompositeService.updateProduct(productId, cat);
	}

	@RequestMapping(value = "/product/{productId}/", method = RequestMethod.DELETE)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") long productId) {
		return productsCompositeService.deleteProduct(productId);
	}

	@RequestMapping(value = "/product/{productId}/price/", method = RequestMethod.POST)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createPrice(@PathVariable("productId") long productId, @RequestBody Price price) {
		return productsCompositeService.createPrice(productId, price);
	}

	@RequestMapping(value = "/product/{productId}/price/", method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Price>> getPrice(@PathVariable("productId") long productId) {
		return productsCompositeService.getPrice(productId);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/product/", method = RequestMethod.POST)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createProduct(@PathVariable("catalogueId") int catalogueId,
			@RequestBody Product product) {
		return productsCompositeService.createProduct(catalogueId, product);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/product/", method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Product>> getProducts(@PathVariable("catalogueId") int catalogueId) {
		return productsCompositeService.getProducts(catalogueId);
	}
	
	@RequestMapping(value = "/catalogue/{catalogueId}/product/{productId}/", method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Product> getProduct(@PathVariable("catalogueId") int catalogueId, @PathVariable("productId") long productId) {
		return productsCompositeService.getProduct(catalogueId, productId);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/product/{productId}/price/" , method = RequestMethod.POST)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createPrice(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, @RequestBody Price price) {
		return productsCompositeService.createPrice(catalogueId, productId, price);
	}

	@RequestMapping(value = "/catalogue/{catalogueId}/product/{productId}/price/" , method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<List<Price>> getPrice(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId){
		return productsCompositeService.getPrice(catalogueId, productId);
	}
	
	@RequestMapping(value = "/catalogue/{catalogueId}/product/{productId}/price/{currency}/" , method = RequestMethod.GET)
	@HystrixCommand(commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Price> getPrice(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, @PathVariable("currency") String currency) {
		return productsCompositeService.getPrice(catalogueId, productId, currency);
	}
}
