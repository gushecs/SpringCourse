package com.sc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.sc.domain.Category;
import com.sc.domain.Product;
import com.sc.repositories.CategoryRepository;
import com.sc.repositories.ProductRepository;
import com.sc.services.exceptions.ObjectNotFoundException;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;
	
	@Autowired
	private CategoryRepository catRepository;

	public Product findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + " Type: " + Product.class.getName()));
	}
	
	public Page<Product> search (String name, List<Integer> ids,Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Category> categories = catRepository.findAllById(ids);
		return repository.findDistinctByNameContainingAndCategoriesIn(name, categories, request);
	}

}
