package com.pricing.core.data;

import java.io.Serializable;

import javax.annotation.Nonnegative;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pricing.core.serializers.PriceSerializer;

@Entity
@Table(name = "price")
@IdClass(PriceId.class)
@JsonSerialize(using = PriceSerializer.class)
@JsonIgnoreProperties( ignoreUnknown = true)
public class Price implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@NotNull
	@Column(name = "product_id")
	@JsonProperty("productId")
	private Long productId;
	@Id
	@NotNull
	@Column(name = "currency")
	@Enumerated(EnumType.STRING)
	@JsonProperty(value = "currency")
	private Currency currency;
	@Column(name = "price")
	@Nonnegative
	@JsonProperty(value = "price")
	private Double price;

	public Price() {
	}

	@JsonCreator
	public Price(@JsonProperty("productId") Long productId, @JsonProperty("currency") Currency currency,
			@JsonProperty("price") Double price) {
		super();
		this.productId = productId;
		this.currency = currency;
		this.price = price;
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
		result = prime * result + (int) (productId ^ (productId >>> 32));
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
		if (productId != other.productId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Price [productId=" + productId + ", currency=" + currency + ", price=" + price + "]";
	}

}
