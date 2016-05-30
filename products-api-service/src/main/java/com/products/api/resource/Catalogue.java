package com.products.api.resource;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.products.api.serializer.CatalogueSerializer;

@JsonSerialize(using = CatalogueSerializer.class)
@JsonIgnoreProperties( ignoreUnknown = true)
public class Catalogue implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty(value = "id")
	private Integer id;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "products")
	private Set<Product> products = new HashSet<Product>();
	public Catalogue() {
		super();
	}
	@JsonCreator
	public Catalogue(@JsonProperty("name")String name) {
		super();
		this.name = name;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Product> getProducts() {
		return products;
	}
	public void setProducts(Set<Product> products) {
		this.products = products;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Catalogue other = (Catalogue) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Catalogue [id=" + id + ", name=" + name +  ", products=" + products + "]";
	}
	
}
