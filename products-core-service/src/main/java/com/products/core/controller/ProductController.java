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
@RequestMapping("/product")
@Transactional
public class ProductController {

	@Autowired
	private ProductRepository productRepo;
	@Autowired
	private CatalogueRepository catalogueRepo;

	@RequestMapping(value = {"/", ""}, method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProduct(@RequestParam(name = "catalogue", required = false) String catalogue,
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "tag", required = false) String tag,
			@RequestParam(name = "description", required = false) String description) {
		List<Product> products = null;
		if (catalogue != null && !"".equals(catalogue.trim())) {
			products = productRepo.retrieveByCatalogue("%" + catalogue.trim().toLowerCase() + "%");
			if (products.isEmpty()) {
				return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		if (name != null && !"".equals(name.trim())) {
			products = productRepo.findByNameContainsIgnoreCase("%" + name.trim().toLowerCase() + "%");
			if (products.isEmpty()) {
				return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		if (tag != null && !"".equals(tag.trim())) {
			products = productRepo.findByTagsContainsIgnoreCase("%" + tag.trim().toLowerCase() + "%");
			if (products.isEmpty()) {
				return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		if (description != null && !"".equals(description.trim())) {
			products = productRepo.findByDescriptionContainsIgnoreCase("%" + description.trim().toLowerCase() + "%");
			if (products.isEmpty()) {
				return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
			}
			return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
		}
		products = productRepo.findAll();
		if (products.isEmpty()) {
			return new ResponseEntity<List<Product>>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	@RequestMapping(value = {"/", ""}, method = RequestMethod.POST)
	public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		if (product != null && product.getName() != null && !"".equals(product.getName().trim())) {
			product = productRepo.save(product);
			HttpHeaders headers = new HttpHeaders();
			headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
			headers.set("productId", String.valueOf(product.getId()));
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		}
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> findByProductId(@PathVariable("id") long id) {
		Product product = productRepo.findOne(id);
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @RequestBody Product cat) {
		Product product = productRepo.findOne(id);
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		if (cat == null || cat.getName() == null || "".equals(cat.getName().trim())) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		product.setName(cat.getName());
		product.setTags(cat.getTags());
		product.setDescription(cat.getDescription());
		productRepo.save(product);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}/associate/{catId}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable("id") long id, @PathVariable("id") int catId) {
		Product product = productRepo.findOne(id);
		if (product == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		if (catId < 1) {
			return new ResponseEntity<Product>(HttpStatus.BAD_REQUEST);
		}
		Catalogue  catalogue = catalogueRepo.findOne(catId);
		if (catalogue == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		product.getCatalogues().add(catalogue);
		catalogue.getProducts().add(product);
		catalogueRepo.save(catalogue);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProduct(@PathVariable("id") long id) {
		Product cat = productRepo.findOne(id);
		if (cat == null) {
			return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
		}
		productRepo.delete(id);
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}

}