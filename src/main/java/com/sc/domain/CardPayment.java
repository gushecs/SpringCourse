package com.sc.domain;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sc.domain.enums.PaymentStatus;

@Entity
@JsonTypeName("cardPayment")
public class CardPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	private Integer parcels;
	
	public CardPayment() {}

	public CardPayment(Integer id, PaymentStatus status, OrderClass order,Integer parcels) {
		super(id, status, order);
		this.setParcels(parcels);
	}

	public Integer getParcels() {
		return parcels;
	}

	public void setParcels(Integer parcels) {
		this.parcels = parcels;
	}

}
