package com.products.composite.service;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.products.composite.resource.Price;

@FeignClient("pricing-core-service")
public interface PricingService {
	@RequestMapping(value = "/product/{id}/price", method = RequestMethod.GET, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<List<Price>> get(@PathVariable("id") long id);
	@RequestMapping(value = "/product/{id}/price", method = RequestMethod.POST, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Void> create(@PathVariable("id") long id, @RequestBody Price price);
	@RequestMapping(value = "/product/{id}/price/{currency}", method = RequestMethod.GET, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Price> get(@PathVariable("id") long id, @PathVariable("currency") String currency) ;
	@RequestMapping(value = "/product/{id}/price/{currency}", method = RequestMethod.PUT, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Price> update(@PathVariable("id") long id, @PathVariable("currency") String currency,
			@RequestBody Price pri) ;
	@RequestMapping(value = "/product/{id}/price/{currency}", method = RequestMethod.DELETE, headers = { "Accept=application/json",
			"content-type= application/json", "accept-encoding= gzip, deflate", "accept-language= en-US,en;q=0.8" })
	public ResponseEntity<Price> delete(@PathVariable("id") long id, @PathVariable("currency") String currency) ;
}
