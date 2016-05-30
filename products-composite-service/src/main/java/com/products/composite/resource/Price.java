package com.products.composite.resource;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.products.composite.serializer.PriceSerializer;

@JsonSerialize(using = PriceSerializer.class)
@JsonIgnoreProperties( ignoreUnknown = true)
public class Price implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@JsonProperty(value = "currency")
	private Currency currency;
	@JsonProperty(value = "price")
	private Double price;

	public Price() {
	}

	@JsonCreator
	public Price(@JsonProperty("currency") Currency currency,
			@JsonProperty("price") Double price) {
		super();
		this.currency = currency;
		this.price = price;
	}
	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((currency == null) ? 0 : currency.hashCode());
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
		Price other = (Price) obj;
		if (currency != other.currency)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Price [currency=" + currency + ", price=" + price + "]";
	}

}
