package com.products.json;

public class ProductType {
	private Integer productTypeId;
	private String productTypeName;
	public ProductType(){}
	public ProductType(Integer productTypeId, String productTypeName) {
		super();
		this.productTypeId = productTypeId;
		this.productTypeName = productTypeName;
	}
	
	public Integer getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getProductTypeName() {
		return productTypeName;
	}
	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((productTypeId == null) ? 0 : productTypeId.hashCode());
		result = prime * result + ((productTypeName == null) ? 0 : productTypeName.hashCode());
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
		ProductType other = (ProductType) obj;
		if (productTypeId == null) {
			if (other.productTypeId != null)
				return false;
		} else if (!productTypeId.equals(other.productTypeId))
			return false;
		if (productTypeName == null) {
			if (other.productTypeName != null)
				return false;
		} else if (!productTypeName.equals(other.productTypeName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProductType [productTypeId=" + productTypeId + ", productTypeName=" + productTypeName + "]";
	}
	
}
