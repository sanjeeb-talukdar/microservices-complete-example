package com.pricing.core.data;

import java.io.Serializable;

public class PriceId implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long productId;
	private Currency currency;
	public PriceId(){}
	public PriceId(Long productId, Currency currency) {
		super();
		this.productId = productId;
		this.currency = currency;
	}

	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Currency getCurrency() {
		return currency;
	}
	public void setCurrency(Currency currency) {
		this.currency = currency;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
		result = prime * result + ((productId == null) ? 0 : productId.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PriceId other = (PriceId) obj;
		if (currency != other.currency)
			return false;
		if (productId == null) {
			if (other.productId != null)
				return false;
		} else if (!productId.equals(other.productId))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "PriceId [productId=" + productId + ", currency=" + currency + "]";
	}
	
}
