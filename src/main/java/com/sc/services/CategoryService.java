package com.sc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.domain.Category;
import com.sc.repositories.CategoryRepository;
import com.sc.services.exceptions.ObjectNotFoundException;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository repository;

	public Category findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + " Type: " + Category.class.getName()));
	}
	
	public Category insert(Category obj) {
		obj.setId(null);
		return repository.save(obj);
	}
	
	public Category update(Category obj) {
		findById(obj.getId());
		return repository.save(obj);
	}

}
