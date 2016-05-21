package com.products.core.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.products.json.Product;
import com.products.json.ProductType;

@Service
public class ProductsServiceImpl implements ProductsService {
	private static final Logger _LOGGER =  LoggerFactory.getLogger(ProductsServiceImpl.class);
	private static final Map<Long, Product> _PRODUCTS = new ConcurrentHashMap<Long, Product>();
	private static final Map<String, List<Product>> _CAT = new ConcurrentHashMap<String, List<Product>>();

	public ProductsServiceImpl() {
		/** Remove that after DB impl */
		List<Product> list1 = new ArrayList<Product>();
		ProductType type1 = new ProductType(1, "cat1");
		_CAT.put(type1.getProductTypeName(), list1);
		_PRODUCTS.put(1L, new Product(type1, 1L, "Product 1"));
		list1.add(_PRODUCTS.get(1L));
		_PRODUCTS.put(2L, new Product(type1, 2L, "Product 2"));
		list1.add(_PRODUCTS.get(2L));
		_PRODUCTS.put(3L, new Product(type1, 3L, "Product 3"));
		list1.add(_PRODUCTS.get(3L));

		List<Product> list2 = new ArrayList<Product>();
		ProductType type2 = new ProductType(2, "cat2");
		_CAT.put(type2.getProductTypeName(), list2);
		_PRODUCTS.put(4L, new Product(type2, 4L, "Product 4"));
		list1.add(_PRODUCTS.get(4L));
		_PRODUCTS.put(5L, new Product(type2, 5L, "Product 5"));
		list1.add(_PRODUCTS.get(5L));
		_PRODUCTS.put(6L, new Product(type2, 6L, "Product 6"));
		list1.add(_PRODUCTS.get(6L));
	}

	@Override
	public Product create(Product product) {
		if (product != null && product.getProductType() != null) {
			Long id = _PRODUCTS.size() + 1L;
			_PRODUCTS.put(id, product);
			List<Product> list = _CAT.get(product.getProductType().getProductTypeName());
			if (list == null)
				list = new ArrayList<Product>();
			_CAT.put(product.getProductType().getProductTypeName(), list);
			list.add(product);
		}
		return product;
	}

	@Override
	public String delete(Long product) {
		if (product != null && _PRODUCTS.get(product) != null) {
			Product temp = _PRODUCTS.remove(product);
			List<Product> list = _CAT.get(temp.getProductType().getProductTypeName());
			if (list != null && !list.isEmpty()) {
				list.remove(temp);
				return "success";
			}
			return "no such cat";
		}
		return "no such product";
	}

	@Override
	public Product findById(Long productId) {
		return _PRODUCTS.get(productId);
	}

	@Override
	public Collection<Product> findByProductType(String productTypeName) {
		if (productTypeName != null && !"".equals(productTypeName.trim())) {
			_CAT.get(productTypeName);
		}
		return null;
	}

}
