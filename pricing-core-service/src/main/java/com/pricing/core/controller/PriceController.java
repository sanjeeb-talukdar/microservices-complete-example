package com.pricing.core.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.pricing.core.data.Currency;
import com.pricing.core.data.Price;
import com.pricing.core.data.PriceId;
import com.pricing.core.data.repository.PriceRepository;

@Transactional
@RestController
public class PriceController {

	@Autowired
	private PriceRepository priceRepo;
	
	@RequestMapping(value = {"/product", "/product/"}, method = RequestMethod.GET)
	public ResponseEntity<List<Price>> getPrice(@RequestParam("ids") Collection<Long> ids) {
		List<Price> prices = priceRepo.findByProductIdIn(ids);
		if (prices.isEmpty()) {
			return new ResponseEntity<List<Price>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Price>>(prices, HttpStatus.OK);
	}

	@RequestMapping(value = {"/product/{id}/price", "/product/{id}/price/"}, method = RequestMethod.GET)
	public ResponseEntity<List<Price>> getPrice(@PathVariable("id") long id) {
		List<Price> prices = priceRepo.findByProductId(id);
		if (prices.isEmpty()) {
			return new ResponseEntity<List<Price>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Price>>(prices, HttpStatus.OK);
	}

	@RequestMapping(value = {"/product/{id}/price", "/product/{id}/price/"}, method = RequestMethod.POST)
	public ResponseEntity<Void> createPrice(@PathVariable("id") long id, @RequestBody Price price,
			UriComponentsBuilder ucBuilder) {
		if (price != null && price.getCurrency() != null) {
			Price created = priceRepo.findOne(new PriceId(id, price.getCurrency()));
			if (created != null) {
				return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
			}
			price.setProductId(id);
			created = priceRepo.save(price);
			HttpHeaders headers = new HttpHeaders();
			headers.set("currency", created.getCurrency().getName());
			headers.set("productId", String.valueOf(created.getProductId()));
			headers.setLocation(ucBuilder.path("/product/{id}/price/{currency}").buildAndExpand(created.getProductId(), created.getCurrency().getName()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = { "/product/{id}/price/{currency}", "/product/{id}/price/{currency}/" }, method = RequestMethod.GET)
	public ResponseEntity<Price> getPrice(@PathVariable("id") long id, @PathVariable("currency") String currency) {
		if (currency != null && !"".equalsIgnoreCase(currency.trim())) {
			Price price = priceRepo.findOne(new PriceId(id, Currency.valueOf(currency.trim().toUpperCase())));
			if (price == null) {
				return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<Price>(price, HttpStatus.OK);
		}
		return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value = { "/product/{id}/price/{currency}", "/product/{id}/price/{currency}/" }, method = RequestMethod.PUT)
	public ResponseEntity<Price> updatePrice(@PathVariable("id") long id, @PathVariable("currency") String currency,
			@RequestBody Price pri) {
		if (currency != null && pri != null && pri.getCurrency() != null) {
			Price price = priceRepo.findOne(new PriceId(id, Currency.valueOf(currency)));
			if (price == null) {
				return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
			}
			price.setProductId(id);
			price.setCurrency(pri.getCurrency());
			price.setPrice(pri.getPrice());
			priceRepo.save(price);
			return new ResponseEntity<Price>(price, HttpStatus.OK);
		}
		return new ResponseEntity<Price>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = { "/product/{id}/price/{currency}", "/product/{id}/price/{currency}/" }, method = RequestMethod.DELETE)
	public ResponseEntity<Price> deletePrice(@PathVariable("id") long id, @PathVariable("currency") String currency) {
		Price cat = priceRepo.findOne(new PriceId(id, Currency.valueOf(currency)));
		if (cat == null) {
			return new ResponseEntity<Price>(HttpStatus.NOT_FOUND);
		}
		priceRepo.delete(cat);
		return new ResponseEntity<Price>(HttpStatus.NO_CONTENT);
	}

}