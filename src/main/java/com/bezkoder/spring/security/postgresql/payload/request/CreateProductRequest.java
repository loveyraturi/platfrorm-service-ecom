package com.bezkoder.spring.security.postgresql.payload.request;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;

public class CreateProductRequest {
	@NotBlank
	private String name;

	private Map<String,Object> sizesAndPrices;
	
	@NotBlank
	private String description;

	@NotBlank
	private String categoryId;

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

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
}
