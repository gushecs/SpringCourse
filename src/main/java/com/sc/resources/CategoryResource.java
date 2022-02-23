package com.sc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sc.domain.Category;
import com.sc.services.CategoryService;

@RestController
@RequestMapping (value = "/categories")
public class CategoryResource {
	
	@Autowired
	CategoryService service;

	@RequestMapping(value ="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findCategory(@PathVariable Integer id) {		
		return ResponseEntity.ok().body(service.findById(id));
	}
	
}
