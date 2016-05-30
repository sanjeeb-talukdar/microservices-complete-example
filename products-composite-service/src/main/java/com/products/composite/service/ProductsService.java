package com.products.composite.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.products.composite.resource.Product;

@FeignClient("products-core-service")
public interface ProductsService {
	@RequestMapping(value = "/product/", method = RequestMethod.GET, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<List<Product>> get(@RequestParam(name = "catalogue", required = false) String catalogue,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "tag", required = false) String tag,
			@RequestParam(name = "description", required = false) String description);
	@RequestMapping(value = "/product/", method = RequestMethod.POST, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Void> create(@RequestBody Product product);
	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Product> findById(@PathVariable("id") long id);
	@RequestMapping(value = "/product/{id}", method = RequestMethod.PUT, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Product> update(@PathVariable("id") long id, @RequestBody Product cat);
	@RequestMapping(value = "/product/{id}/associate/{catId}", method = RequestMethod.PUT, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Product> update(@PathVariable("id") long id, @PathVariable("id") int catId);
	@RequestMapping(value = "/product/{id}", method = RequestMethod.DELETE, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Product> delete(@PathVariable("id") long id);
	
}
