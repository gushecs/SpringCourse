package com.sc.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OrderClass implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Date moment;
	
	@OneToOne (cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn (name = "billing_address_id")
	private Address billingAddress;
	
	public OrderClass() {}

	public OrderClass(Integer id, Date moment, Client client, Address billingAddress) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
		this.billingAddress = billingAddress;
	}

	public Integer getId() {
		return id;
	}

	public Date getMoment() {
		return moment;
	}

	public Payment getPayment() {
		return payment;
	}

	public Client getClient() {
		return client;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
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
		OrderClass other = (OrderClass) obj;
		return Objects.equals(id, other.id);
	};

}
