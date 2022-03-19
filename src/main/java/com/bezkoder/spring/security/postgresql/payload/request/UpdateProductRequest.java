package com.bezkoder.spring.security.postgresql.payload.request;

import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.constraints.*;
 
public class UpdateProductRequest {
	@NotBlank
	private String productId;
	
	@NotBlank
	private String name;

	@NotBlank
	private Map<String,Object> sizesAndPrices;
	
	@NotBlank
	private String description;

	@NotBlank
	private int categoryId;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Object> getSizesAndPrices() {
		return sizesAndPrices;
	}

	public void setSizesAndPrices(Map<String, Object> sizesAndPrices) {
		this.sizesAndPrices = sizesAndPrices;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	
}
