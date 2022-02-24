package com.sc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.domain.OrderClass;
import com.sc.repositories.OrderClassRepository;
import com.sc.services.exceptions.ObjectNotFoundException;

@Service
public class OrderClassService {

	@Autowired
	private OrderClassRepository repository;

	public OrderClass findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + " Type: " + OrderClass.class.getName()));
	}

}
