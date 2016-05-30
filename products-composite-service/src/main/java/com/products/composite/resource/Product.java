package com.products.composite.resource;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.products.composite.serializer.ProductSerializer;

@JsonSerialize(using = ProductSerializer.class)
@JsonIgnoreProperties( ignoreUnknown = true)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@JsonProperty(value = "id")
	private Long id;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "description")
	private String description;
	@JsonProperty(value = "tags")
	private String tags;
	@JsonProperty(value = "prices")
	private Set<Price> prices = new HashSet<Price>();
	public Product() {
	}
	@JsonCreator
	public Product(@JsonProperty("name")String name, @JsonProperty("description")String description, @JsonProperty("tags")String tags) {
		super();
		this.name = name;
		this.description = description;
		this.tags = tags;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public Set<Price> getPrices() {
		return prices;
	}
	
	public void setPrices(Set<Price> prices) {
		this.prices = prices;
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
		Product other = (Product) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description
				+ ", tags=" + tags + "]";
	}

}
