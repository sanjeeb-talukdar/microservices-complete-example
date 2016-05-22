/*package com.products.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.products.core.data.Product;
import com.products.core.data.repository.ProductRepository;

@Controller
@ExposesResourceFor(Product.class)
public class ProductController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private EntityLinks entityLinks;

	@RequestMapping(path = "/catalogue/{catalogueId}/products", method = {
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Resources<Product>> getAllProducts(@PathVariable("catalogueId") Integer catalogueId,
			@RequestParam(value = "name", required = false) String name,
			@RequestParam(value = "tags", required = false) String tags,
			@RequestParam(value = "description", required = false) String desc) {
		if (name == null && tags == null && desc == null) {
			System.out.println("%%%%%%%%%%%%%1");
			Resources<Product> resources = new Resources<Product>(this.productRepository
					.findByCatalogue_IdAndNameContainsAndTagsContainsAndDescriptionContainsIgnoreCase(catalogueId, name,
							tags, desc));
			System.out.println("%%%%%%%%%%%%%2");
			resources.add(this.entityLinks.linkToCollectionResource(Product.class));
			System.out.println("%%%%%%%%%%%%%3");
			return new ResponseEntity<Resources<Product>>(resources, HttpStatus.OK);
		} else {
			System.out.println("%%%%%%%%%%%%%4");
			Resources<Product> resources = new Resources<Product>(
					this.productRepository.findByCatalogue_Id(catalogueId));
			System.out.println("%%%%%%%%%%%%%5");
			resources.add(this.entityLinks.linkToCollectionResource(Product.class));
			System.out.println("%%%%%%%%%%%%%6");
			return new ResponseEntity<Resources<Product>>(resources, HttpStatus.OK);
		}
	}

	@RequestMapping(path = "/catalogue/{catalogueId}/products/{productId}", method = {
			RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Resource<Product>> getAllProductsById(
			@PathVariable("catalogueId") Integer catalogueId, @PathVariable("productId") Long productId) {
		Product p = this.productRepository.findByCatalogue_IdAndId(catalogueId, productId);
		Resource<Product> resources = new Resource<Product>(p);
		resources.add(this.entityLinks.linkToSingleResource(Product.class, p.getId()));
		return new ResponseEntity<Resource<Product>>(resources, HttpStatus.OK);
	}

	@RequestMapping(path = "/products", method = { RequestMethod.GET }, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody HttpEntity<Resources<Product>> getAllProductsByCatalogueName(
			@RequestParam(value = "catalogue", required = false) String catalogue) {
		Resources<Product> resources = new Resources<Product>(
				this.productRepository.findByCatalogue_NameContainsIgnoreCase(catalogue));
		resources.add(this.entityLinks.linkToCollectionResource(Product.class));
		return new ResponseEntity<Resources<Product>>(resources, HttpStatus.OK);
	}

}
*/