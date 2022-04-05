package com.sc.resources;

import java.net.URI;

import javax.validation.Valid;

import com.sc.domain.DTO.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody OrderClass obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<OrderClass>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
													  @RequestParam(value = "lines", defaultValue = "24") Integer linesPerPage,
													  @RequestParam(value = "order", defaultValue = "moment") String orderBy,
													  @RequestParam(value = "dir", defaultValue = "DESC") String direction) {
		return ResponseEntity.ok()
				.body(service.findPage(page, linesPerPage, orderBy, direction));
	}
	
}
