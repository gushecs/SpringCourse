package com.sc.domain.DTO;

import java.io.Serializable;

import com.sc.domain.Product;

public class ProductDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Double price;
	
	public ProductDTO() {}
	
	public ProductDTO(Product obj) {
		name=obj.getName();
		id=obj.getId();
		price=obj.getPrice();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Double getPrice() {
		return price;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
