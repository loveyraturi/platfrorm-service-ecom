package com.datafroth.modal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Taxes {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	int id;
	int taxPercentage;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTaxPercentage() {
		return taxPercentage;
	}
	public void setTaxPercentage(int taxPercentage) {
		this.taxPercentage = taxPercentage;
	}
	
	
}
