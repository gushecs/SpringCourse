package com.sc.resources;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sc.domain.Category;
import com.sc.domain.OrderClass;
import com.sc.domain.DTO.CategoryDTO;
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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OrderClass obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
}
