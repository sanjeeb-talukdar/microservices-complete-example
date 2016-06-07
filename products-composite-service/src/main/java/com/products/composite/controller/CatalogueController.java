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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.products.composite.resource.Catalogue;
import com.products.composite.resource.Product;
import com.products.composite.service.CatalogueService;
import com.products.composite.service.PricingService;
import com.products.composite.util.PricingUtil;

@RestController
@RequestMapping("/catalogue")
public class CatalogueController {

	@Autowired
	private PricingService pricingService;
	@Autowired
	private CatalogueService catalogueService;

	@RequestMapping(value = { "", "/"}, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "getCataloguesFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false")
			})
	public ResponseEntity<List<Catalogue>> getCompCatalogues(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "attachPrice", required = false, defaultValue = "true") boolean attachPrice,
			UriComponentsBuilder ucBuilder) {
		ResponseEntity<List<Catalogue>> catalogues = catalogueService.getCatalogues(name);
		if (catalogues != null && HttpStatus.OK.equals(catalogues.getStatusCode())) {
			List<Product> products = new ArrayList<Product>();
			List<Catalogue> cataloguesList = catalogues.getBody();
			if (attachPrice) {
				for (Catalogue catalogue : cataloguesList) {
					if (catalogue.getProducts() != null && !catalogue.getProducts().isEmpty()) {
						products.addAll(catalogue.getProducts());
					}
				}
				if (!products.isEmpty()) {
					PricingUtil.attachPrice(products, pricingService);
				}
			}
			if (cataloguesList.isEmpty()) {
				return new ResponseEntity<List<Catalogue>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Catalogue>>(cataloguesList, HttpStatus.OK);
		}
		return new ResponseEntity<List<Catalogue>>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<List<Catalogue>> getCataloguesFallBack(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "attachPrice", required = false, defaultValue = "true") boolean attachPrice,
			UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "getCataloguesFallBack");
		return new ResponseEntity<List<Catalogue>>(headers, HttpStatus.FOUND);
	}

	@RequestMapping(value = { "/{catalogueId}", "/{catalogueId}/" }, method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "findByCatalogueIdFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> findByCompCatalogueId(@PathVariable("catalogueId") int catalogueId,
			UriComponentsBuilder ucBuilder) {
		ResponseEntity<Catalogue> response = catalogueService.findByCatalogueId(catalogueId);
		if (response != null && HttpStatus.OK.equals(response.getStatusCode())) {
			Catalogue catalogue = response.getBody();
			if (catalogue != null && !catalogue.getProducts().isEmpty()) {
				PricingUtil.attachPrice(new ArrayList<Product>(catalogue.getProducts()), pricingService);
			}
			return new ResponseEntity<Catalogue>(catalogue, HttpStatus.OK);
		}
		return new ResponseEntity<Catalogue>(HttpStatus.NOT_FOUND);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Catalogue> findByCatalogueIdFallBack(@PathVariable("catalogueId") int catalogueId,
			UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "findByCatalogueIdFallBack");
		return new ResponseEntity<Catalogue>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	@HystrixCommand(fallbackMethod = "createCatalogueFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Void> createCompCatalogue(@RequestBody Catalogue catalogue, UriComponentsBuilder ucBuilder) {
		return catalogueService.createCatalogue(catalogue);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Void> createCatalogueFallBack(@RequestBody Catalogue catalogue, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "createCatalogueFallBack");
		return new ResponseEntity<Void>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{catalogueId}", "/{catalogueId}/" }, method = RequestMethod.PUT)
	@HystrixCommand(fallbackMethod = "updateCatalogueFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> updateCompCatalogue(@PathVariable("catalogueId") int catalogueId,
			@RequestBody Catalogue catalogue, UriComponentsBuilder ucBuilder) {
		return catalogueService.updateCatalogue(catalogueId, catalogue);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Catalogue> updateCatalogueFallBack(@PathVariable("catalogueId") int catalogueId,
			@RequestBody Catalogue catalogue, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "updateCatalogueFallBack");
		return new ResponseEntity<Catalogue>(catalogue, headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{catalogueId}", "/{catalogueId}/" }, method = RequestMethod.DELETE)
	@HystrixCommand(fallbackMethod = "deleteCatalogueFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> deleteCompCatalogue(@PathVariable("catalogueId") int catalogueId,
			UriComponentsBuilder ucBuilder) {
		return catalogueService.deleteCatalogue(catalogueId);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Catalogue> deleteCatalogueFallBack(@PathVariable("catalogueId") int catalogueId,
			UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "updateCatalogueFallBack");
		return new ResponseEntity<Catalogue>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{catalogueId}/attach/{productId}",
			"/{catalogueId}/attach/{productId}/" }, method = RequestMethod.PUT)
	@HystrixCommand(fallbackMethod = "attach2CatalogueFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> attach2CompCatalogue(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, UriComponentsBuilder ucBuilder) {
		return catalogueService.attachProduct(catalogueId, productId);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Catalogue> attach2CatalogueFallBack(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "attachCatalogueFallBack");
		return new ResponseEntity<Catalogue>(headers, HttpStatus.ACCEPTED);
	}

	@RequestMapping(value = { "/{catalogueId}/detach/{productId}",
			"/{catalogueId}/detach/{productId}/" }, method = RequestMethod.PUT)
	@HystrixCommand(fallbackMethod = "detachFromCatalogueFallBack", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "THREAD"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
			@HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000000"),
			@HystrixProperty(name = "execution.timeout.enabled", value = "false") })
	public ResponseEntity<Catalogue> detachCompFromCatalogue(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, UriComponentsBuilder ucBuilder) {
		return catalogueService.detachProduct(catalogueId, productId);
	}

	@SuppressWarnings("unused")
	private ResponseEntity<Catalogue> detachFromCatalogueFallBack(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, UriComponentsBuilder ucBuilder) {
		/** TODO Implement event driven plan B */
		HttpHeaders headers = new HttpHeaders();
		headers.set("fallBack", "detachCatalogueFallBack");
		return new ResponseEntity<Catalogue>(headers, HttpStatus.ACCEPTED);
	}

}
