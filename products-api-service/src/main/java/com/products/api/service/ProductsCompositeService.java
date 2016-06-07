package com.products.api.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.products.api.resource.Catalogue;
import com.products.api.resource.Price;
import com.products.api.resource.Product;

@RestController
@FeignClient(name = "products-composite-service", configuration = {FeignConfiguration.class})
public interface ProductsCompositeService {
	@RequestMapping(value = "/catalogue/", method = RequestMethod.GET, headers = { "accept= application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<List<Catalogue>> getCatalogues(@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "attachPrice", required = false, defaultValue = "true") boolean attachPrice);

	@RequestMapping(value = "/catalogue/{catalogueId}/", method = RequestMethod.GET, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> findByCatalogueId(@PathVariable("catalogueId") int catalogueId);

	@RequestMapping(value = "/catalogue/", method = RequestMethod.POST, headers = { "accept= application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Void> createCatalogue(@RequestBody Catalogue catalogue);

	@RequestMapping(value = "/catalogue/{catalogueId}/", method = RequestMethod.PUT, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> updateCatalogue(@PathVariable("catalogueId") int catalogueId,
			@RequestBody Catalogue catalogue);

	@RequestMapping(value = "/catalogue/{catalogueId}/", method = RequestMethod.DELETE, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> deleteCatalogue(@PathVariable("catalogueId") int catalogueId);

	@RequestMapping(value = "/catalogue/{catalogueId}/attach/{productId}/", method = RequestMethod.PUT, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> attach2Catalogue(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId);

	@RequestMapping(value = "/catalogue/{catalogueId}/detach/{productId}/", method = RequestMethod.PUT, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> detachFromCatalogue(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId);

	@RequestMapping(value = "/product/", method = RequestMethod.GET, headers = { "accept= application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<List<Product>> getProducts(
			@RequestParam(name = "catalogue", required = false) String catalogue,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "tag", required = false) String tag,
			@RequestParam(name = "description", required = false) String description);

	@RequestMapping(value = "/product/", method = RequestMethod.POST, headers = { "accept= application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Void> createProduct(@RequestBody Product product);

	@RequestMapping(value = "/product/{productId}/", method = RequestMethod.GET, headers = { "accept= application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Product> findByProductId(@PathVariable("productId") long productId);

	@RequestMapping(value = "/product/{productId}/", method = RequestMethod.PUT, headers = { "accept= application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Product> updateProduct(@PathVariable("productId") long productId, @RequestBody Product cat);

	@RequestMapping(value = "/product/{productId}/", method = RequestMethod.DELETE, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") long productId);

	@RequestMapping(value = "/product/{productId}/price/", method = RequestMethod.POST, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Void> createPrice(@PathVariable("productId") long productId, @RequestBody Price price);

	@RequestMapping(value = "/product/{productId}/price/", method = RequestMethod.GET, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<List<Price>> getPrice(@PathVariable("productId") long productId);

	@RequestMapping(value = "/catalogue/{catalogueId}/product/", method = RequestMethod.POST, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Void> createProduct(@PathVariable("catalogueId") int catalogueId,
			@RequestBody Product product);

	@RequestMapping(value = "/catalogue/{catalogueId}/product/", method = RequestMethod.GET, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<List<Product>> getProducts(@PathVariable("catalogueId") int catalogueId);

	@RequestMapping(value = "/catalogue/{catalogueId}/product/{productId}/", method = RequestMethod.GET, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Product> getProduct(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId);

	@RequestMapping(value = "/catalogue/{catalogueId}/product/{productId}/price/", method = RequestMethod.POST, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Void> createPrice(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, @RequestBody Price price);

	@RequestMapping(value = "/catalogue/{catalogueId}/product/{productId}/price/", method = RequestMethod.GET, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<List<Price>> getPrice(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId);

	@RequestMapping(value = "/catalogue/{catalogueId}/product/{productId}/price/{currency}/", method = RequestMethod.GET, headers = {
			"accept= application/json", "content-type= application/json", "accept-encoding= gzip, deflate",
			"accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Price> getPrice(@PathVariable("catalogueId") int catalogueId,
			@PathVariable("productId") long productId, @PathVariable("currency") String currency);

}
