package com.products.core.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.products.core.data.Catalogue;
import com.products.core.data.Product;
import com.products.core.data.repository.CatalogueRepository;
import com.products.core.data.repository.ProductRepository;

@RestController
@RequestMapping("/catalogue")
@Transactional
public class CatalogueController {

	@Autowired
	private CatalogueRepository catalogueRepo;
	@Autowired
	private ProductRepository productRepo;

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<List<Catalogue>> get(@RequestParam(name = "name", required = false) String name) {
		List<Catalogue> catalogues = null;
		if (name != null && !"".equals(name.trim())) {
			catalogues = catalogueRepo.findByNameContainsIgnoreCase("%" + name.trim().toLowerCase() + "%");
			if (catalogues.isEmpty()) {
				return new ResponseEntity<List<Catalogue>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Catalogue>>(catalogues, HttpStatus.OK);
		}
		catalogues = catalogueRepo.findAll();
		if (catalogues.isEmpty()) {
			return new ResponseEntity<List<Catalogue>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Catalogue>>(catalogues, HttpStatus.OK);
	}

	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
	public ResponseEntity<Void> create(@RequestBody Catalogue catalogue, UriComponentsBuilder ucBuilder) {
		if (catalogue != null && catalogue.getName() != null && !"".equals(catalogue.getName().trim())) {
			catalogue = catalogueRepo.save(catalogue);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/catalogue/{id}").buildAndExpand(catalogue.getId()).toUri());
			headers.set("catalogueId", String.valueOf(catalogue.getId()));
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Catalogue> findById(@PathVariable("id") int id) {
		Catalogue catalogue = catalogueRepo.findOne(id);
		if (catalogue == null) {
			return new ResponseEntity<Catalogue>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Catalogue>(catalogue, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Catalogue> update(@PathVariable("id") int id, @RequestBody Catalogue cat) {
		Catalogue catalogue = catalogueRepo.findOne(id);
		if (catalogue == null) {
			return new ResponseEntity<Catalogue>(HttpStatus.NOT_FOUND);
		}
		if (cat == null || cat.getName() == null || "".equals(cat.getName().trim())) {
			return new ResponseEntity<Catalogue>(HttpStatus.BAD_REQUEST);
		}
		catalogue.setName(cat.getName());
		catalogueRepo.save(catalogue);
		return new ResponseEntity<Catalogue>(catalogue, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/attach/{prodId}", method = RequestMethod.PUT)
	public ResponseEntity<Catalogue> attach(@PathVariable("id") int id, @PathVariable("prodId") long prod) {
		if (id < 1 || prod < 1) {
			return new ResponseEntity<Catalogue>(HttpStatus.BAD_REQUEST);
		}
		Catalogue catalogue = catalogueRepo.findOne(id);
		if (catalogue == null) {
			return new ResponseEntity<Catalogue>(HttpStatus.NOT_FOUND);
		}
		Product product   = productRepo.findOne(prod);
		if (product == null) {
			return new ResponseEntity<Catalogue>(HttpStatus.NOT_FOUND);
		}
		product.getCatalogues().add(catalogue);
		catalogue.getProducts().add(product);
		catalogueRepo.save(catalogue);productRepo.save(product);
		return new ResponseEntity<Catalogue>(catalogue, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/detach/{prodId}", method = RequestMethod.PUT)
	public ResponseEntity<Catalogue> detach(@PathVariable("id") int id, @PathVariable("prodId") long prod) {
		if (id < 1 || prod < 1) {
			return new ResponseEntity<Catalogue>(HttpStatus.BAD_REQUEST);
		}
		Catalogue catalogue = catalogueRepo.findOne(id);
		if (catalogue == null) {
			return new ResponseEntity<Catalogue>(HttpStatus.NOT_FOUND);
		}
		Product product   = productRepo.findOne(prod);
		if (product == null) {
			return new ResponseEntity<Catalogue>(HttpStatus.NOT_FOUND);
		}
		product.getCatalogues().remove(catalogue);
		catalogue.getProducts().remove(product);
		catalogueRepo.save(catalogue);productRepo.save(product);
		return new ResponseEntity<Catalogue>(catalogue, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Catalogue> delete(@PathVariable("id") int id) {
		Catalogue cat = catalogueRepo.findOne(id);
		if (cat == null) {
			return new ResponseEntity<Catalogue>(HttpStatus.NOT_FOUND);
		}
		catalogueRepo.delete(id);
		return new ResponseEntity<Catalogue>(HttpStatus.NO_CONTENT);
	}

}