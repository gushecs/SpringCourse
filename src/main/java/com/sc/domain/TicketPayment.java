package com.sc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sc.domain.enums.PaymentStatus;

@Entity
public class TicketPayment extends Payment{
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date expiringDate;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date paymentDate;
	
	public TicketPayment() {}

	public TicketPayment(Integer id, PaymentStatus status, OrderClass order, Date expiringDate, Date paymentDate) {
		super(id, status, order);
		this.expiringDate=expiringDate;
		this.paymentDate=paymentDate;
	}

	public Date getExpiringDate() {
		return expiringDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setExpiringDate(Date expiringDate) {
		this.expiringDate = expiringDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

}
