package com.pricing.core.service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.products.json.Price;

@Service
public class PricingCoreServiceImpl implements PricingCoreService {

	private static final Logger _LOGGER =  LoggerFactory.getLogger(PricingCoreServiceImpl.class);
	private static final Map<Long, Price> _PRODUCTS = new ConcurrentHashMap<Long, Price>();
	
	public PricingCoreServiceImpl() {
		/** Remove that after DB impl */
		_PRODUCTS.put(1L, new Price(1L, 100D));
		_PRODUCTS.put(2L, new Price(2L, 200D));
		_PRODUCTS.put(3L, new Price(3L, 300D));
		_PRODUCTS.put(4L, new Price(4L, 400D));
		_PRODUCTS.put(5L, new Price(5L, 500D));
		_PRODUCTS.put(6L, new Price(6L, 600D));
	}
	
	@Override
	public Price create(Long productId, Double price) {
		if(productId!= null && productId>0 && price != null) {
			_PRODUCTS.put(productId, new Price(productId, price));
		}
		return _PRODUCTS.get(productId);
	}

	@Override
	public String delete(Long productId) {
		if(productId!= null && productId>0 && _PRODUCTS.containsKey(productId)) {
			_PRODUCTS.remove(productId);
			return "success";
		}
		return "failure";
	}

	@Override
	public Price findById(Long productId) {
		return _PRODUCTS.get(productId);
	}

	

}
