package com.products.composite.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.products.composite.resource.Catalogue;
@FeignClient( name = "products-core-service", configuration ={ FeignConfiguration.class} )
public interface CatalogueService {
	@RequestMapping(value = "/catalogue/", method = RequestMethod.GET, headers = { "accept= application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<List<Catalogue>> getCatalogues(@RequestParam(name = "name", required = false) String name);
	@RequestMapping(value = "/catalogue/", method = RequestMethod.POST, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Void> createCatalogue(@RequestBody Catalogue catalogue);
	@RequestMapping(value = "/catalogue/{id}", method = RequestMethod.GET, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> findByCatalogueId(@PathVariable("id") int id);
	@RequestMapping(value = "/catalogue/{id}", method = RequestMethod.PUT, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> updateCatalogue(@PathVariable("id") int id, @RequestBody Catalogue cat);
	@RequestMapping(value = "/catalogue/{id}/attach/{prodId}", method = RequestMethod.PUT, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> attachProduct(@PathVariable("id") int id, @PathVariable("prodId") long prod);
	@RequestMapping(value = "/catalogue/{id}/detach/{prodId}", method = RequestMethod.PUT, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> detachProduct(@PathVariable("id") int id, @PathVariable("prodId") long prod);
	@RequestMapping(value = "/catalogue/{id}", method = RequestMethod.DELETE, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Catalogue> deleteCatalogue(@PathVariable("id") int id);
}
