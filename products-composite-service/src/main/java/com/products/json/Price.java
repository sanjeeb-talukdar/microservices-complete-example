package com.products.json;

public class Price {
	private Long productId;
	private Double price;
	public Price(){}
	public Price(Long productId, Double price) {
		super();
		this.productId = productId;
		this.price = price;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
}
