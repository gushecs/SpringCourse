package com.sc.domain;

import java.io.Serializable;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Locale;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class OrderClass implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm")
	private Date moment;
	
	@OneToOne (cascade = CascadeType.ALL, mappedBy = "order")
	private Payment payment;
	
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	
	@ManyToOne
	@JoinColumn (name = "billing_address_id")
	private Address billingAddress;
	
	@OneToMany (mappedBy = "id.order")
	private Set<OrderItem> items = new HashSet<>();
	
	public OrderClass() {}

	public OrderClass(Integer id, Date moment, Client client, Address billingAddress) {
		super();
		this.id = id;
		this.moment = moment;
		this.client = client;
		this.billingAddress = billingAddress;
	}
	
	public double getTotalValue() {
		return items.stream().mapToDouble(x -> x.getSubTotal()).sum();
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

	public Set<OrderItem> getItems() {
		return items;
	}

	public void setItems(Set<OrderItem> items) {
		this.items = items;
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
	}

	@Override
	public String toString() {
		NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt","BR"));
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
		StringBuilder builder = new StringBuilder();
		builder.append("Order number: ");
		builder.append(getId());
		builder.append(", moment: ");
		builder.append(sdf.format(getMoment()));
		builder.append(", client: ");
		builder.append(getClient().getName());
		builder.append(", payment situation: ");
		builder.append(getPayment().getStatus().getDescription());
		builder.append("\nDetails:\n");
		getItems().forEach(x -> builder.append(x.toString()));
		builder.append("Total value: ");
		builder.append(nf.format(getTotalValue()));
		return builder.toString();
	};

}
