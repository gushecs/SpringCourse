package com.sc.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sc.domain.enums.PaymentStatus;

@Entity
@Inheritance (strategy = InheritanceType.JOINED)
public abstract class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	private Integer status;
	
	@OneToOne
	@JoinColumn(name = "order_id")
	@MapsId
	@JsonBackReference
	private OrderClass order;
	
	public Payment() {}

	public Payment(Integer id, PaymentStatus status, OrderClass order) {
		super();
		this.id = id;
		this.status = status.getCod();
		this.order = order;
	}

	public Integer getId() {
		return id;
	}

	public PaymentStatus getStatus() {
		return PaymentStatus.toEnum(status);
	}

	public OrderClass getOrder() {
		return order;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status.getCod();
	}

	public void setOrder(OrderClass order) {
		this.order = order;
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
		Payment other = (Payment) obj;
		return Objects.equals(id, other.id);
	}

}
