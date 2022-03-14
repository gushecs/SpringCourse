package com.sc.services;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.domain.OrderClass;
import com.sc.domain.OrderItem;
import com.sc.domain.TicketPayment;
import com.sc.domain.enums.PaymentStatus;
import com.sc.repositories.OrderClassRepository;
import com.sc.repositories.OrderItemRepository;
import com.sc.repositories.PaymentRepository;
import com.sc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderClassService {

	@Autowired
	private OrderClassRepository repository;
	
	@Autowired
	private TicketService ticketService;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;

	public OrderClass findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + " Type: " + OrderClass.class.getName()));
	}
	
	@Transactional
	public OrderClass insert(OrderClass obj) {
		obj.setId(null);
		obj.setMoment(new Date());
		obj.getPayment().setStatus(PaymentStatus.WAITING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof TicketPayment) {
			TicketPayment payment = (TicketPayment) obj.getPayment();
			ticketService.fillTicketPayment(payment, obj.getMoment());
		}
		obj = repository.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem oi:obj.getItems()) {
			oi.setDeduction(0.00);
			oi.setPrice(productService.findById(oi.getProduct().getId()).getPrice());
			oi.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		return obj;
	}

}
