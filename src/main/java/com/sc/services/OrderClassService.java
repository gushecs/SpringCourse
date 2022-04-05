package com.sc.services;

import java.util.Date;

import javax.transaction.Transactional;

import com.sc.domain.*;
import com.sc.security.UserSS;
import com.sc.services.exceptions.AuthorizationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

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
	private ClientService clientService;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Autowired
	private MailService mailService;

	public OrderClass findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + " Type: " + OrderClass.class.getName()));
	}
	
	@Transactional
	public OrderClass insert(OrderClass obj) {
		obj.setId(null);
		obj.setMoment(new Date());
		obj.setClient(clientService.findById(obj.getClient().getId()));
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
			oi.setProduct(productService.findById(oi.getProduct().getId()));
			oi.setPrice(oi.getProduct().getPrice());
			oi.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItems());
		mailService.sendOrderConfirmationHtmlMail(obj);
		return obj;
	}

	public Page<OrderClass> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSS user = UserService.authenticated();

		if (user==null) {
			throw new AuthorizationException("Denied Access");
		}

		PageRequest request = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
		Client client = clientService.findById(user.getId());
		return repository.findByClient(client,request);
	}

}
