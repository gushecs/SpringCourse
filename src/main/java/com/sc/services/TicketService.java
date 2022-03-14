package com.sc.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.sc.domain.TicketPayment;

@Service
public class TicketService {

	public void fillTicketPayment(TicketPayment payment, Date orderMoment) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(orderMoment);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		payment.setExpiringDate(cal.getTime());
	}
	
}
