package com.products.composite.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.products.json.Price;
import com.products.json.Product;

@Service
public class ProductsCompositeServiceImpl implements ProductsCompositeService {
	private static final String _PRODUCT_CORE = "http://products-core-service/products/";
	private static final String _PRICING_CORE = "http://pricing-core-service/products/";
	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void create(Integer categortId, String poductName, Double price) {

		// restTemplate.postForEntity(_PRODUCT_CORE, , Product.class);
	}

	@Override
	public List<Product> read(String producType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String delete(Integer productTypeId, Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product readProduct(Long producId) {
		Product product = restTemplate.getForObject(_PRODUCT_CORE + producId, Product.class);
		Price price= restTemplate.getForObject(_PRICING_CORE + producId +"/price", Price.class);
		product.setPrice(price);
		return product;
	}

	@Override
	public Price readPrice(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

}
