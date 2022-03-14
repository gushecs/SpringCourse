package com.sc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class OrderItem implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	@JsonIgnore
	private OrderItemPK id = new OrderItemPK();
	
	private Double deduction;
	private Integer quantity;
	private Double price;
	
	public OrderItem() {}

	public OrderItem(OrderClass order, Product product, Double deduction, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.deduction = deduction;
		this.quantity = quantity;
		this.price = price;
	}
	
	public double getSubTotal() {
		return (price-deduction)*quantity;
	}

	public OrderItemPK getId() {
		return id;
	}

	public Double getDeduction() {
		return deduction;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Double getPrice() {
		return price;
	}
	
	@JsonIgnore
	public OrderClass getOrder() {
		return id.getOrder();
	}
	
	public Product getProduct() {
		return id.getProduct();
	}

	public void setId(OrderItemPK id) {
		this.id = id;
	}

	public void setDeduction(Double deduction) {
		this.deduction = deduction;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	public void setOrder(OrderClass order) {
		id.setOrder(order);
	}
	
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderItem other = (OrderItem) obj;
		return Objects.equals(id, other.id);
	};
	
}
