package com.sc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sc.domain.Category;
import com.sc.domain.Client;
import com.sc.repositories.ClientRepository;
import com.sc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	public Client findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + " Type: " + Category.class.getName()));
	}

}
