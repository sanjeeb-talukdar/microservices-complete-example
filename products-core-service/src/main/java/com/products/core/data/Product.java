package com.products.core.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.products.core.serializers.ProductSerializer;

@Entity
@Table(name = "product")
@JsonSerialize(using = ProductSerializer.class)
@JsonIgnoreProperties( ignoreUnknown = true)
public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue
	@Column(name = "product_id")
	@JsonProperty(value = "id")
	private Long id;
	@Version
	@Column(name = "product_version")
	@JsonIgnore
	private Integer version;
	@NotNull
	@NaturalId
	@Column(name = "product_name")
	@JsonProperty(value = "name")
	private String name;
	@Column(name = "product_description")
	@JsonProperty(value = "description")
	private String description;
	@Column(name = "product_tags")
	@JsonProperty(value = "tags")
	private String tags;
	@ManyToMany
	@JsonIgnore
	@JoinTable(name = "product_catalogue", joinColumns = @JoinColumn(name = "product_id") , inverseJoinColumns = @JoinColumn(name = "catalogue_id") )
	private Set<Catalogue> catalogues= new HashSet<Catalogue>();

	public Product() {
	}
	@JsonCreator
	public Product(@JsonProperty("id") Long id, @JsonProperty("name")String name, @JsonProperty("description")String description, @JsonProperty("tag")String tags) {
		super();
		this.id = id;
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

	public Set<Catalogue> getCatalogues() {
		return catalogues;
	}

	public void setCatalogues(Set<Catalogue> catalogues) {
		this.catalogues = catalogues;
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
		return "Product [id=" + id + ", version=" + version + ", name=" + name + ", description=" + description
				+ ", tags=" + tags + "]";
	}

}
