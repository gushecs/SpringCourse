package com.sc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sc.domain.OrderClass;
import com.sc.services.OrderClassService;

@RestController
@RequestMapping (value = "/orders")
public class OrderClassResource {
	
	@Autowired
	OrderClassService service;

	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<OrderClass> findOrderClass(@PathVariable Integer id) {		
		return ResponseEntity.ok().body(service.findById(id));
	}
	
}
