package com.sc.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sc.domain.Address;
import com.sc.domain.City;
import com.sc.domain.Client;
import com.sc.domain.DTO.ClientDTO;
import com.sc.domain.DTO.ClientInsertDTO;
import com.sc.domain.enums.ClientType;
import com.sc.repositories.AddressRepository;
import com.sc.repositories.ClientRepository;
import com.sc.services.exceptions.DataIntegrityException;
import com.sc.services.exceptions.ObjectNotFoundException;

@Service
public class ClientService {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private ClientRepository repository;
	
	@Autowired
	private AddressRepository addressRepository;

	public Client findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Object not found! Id: " + id + " Type: " + Client.class.getName()));
	}
	
	@Transactional
	public Client insert(Client obj) {
		obj.setId(null);
		addressRepository.saveAll(obj.getAddresses());
		return repository.save(obj);
	}

	public Client update(Client obj) {
		Client newObj = findById(obj.getId());
		updateData(newObj,obj);
		return repository.save(newObj);
	}

	public void delete(Integer id) {
		try {
			findById(id);
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"You can't delete a client that got orders! Id: " + id + " Type: " + Client.class.getName());
		}
	}

	public List<ClientDTO> findAll() {
		List<Client> list = repository.findAll();
		return list.stream().map(ClientDTO::new).collect(Collectors.toList());
	}

	public Page<Client> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest request = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repository.findAll(request);
	}

	public Client fromDTO(ClientDTO obj) {
		return new Client(obj.getId(), obj.getName(), obj.getEmail(), null, null, null);
	}
	
	public Client fromDTO(ClientInsertDTO obj) {
		Client client = new Client(null,obj.getName(),obj.getEmail(),obj.getCpf_cnpj(),ClientType.toEnum(obj.getClientType()),pe.encode(obj.getPassword()));
		City city = new City(obj.getCityId(),null,null);
		Address address = new Address(null,obj.getStreet(),obj.getNumber(),obj.getComplement(),obj.getDistrict(),obj.getPostalcode(),client,city);
		client.getAddresses().add(address);
		client.getPhones().add(obj.getPhone1());
		if (obj.getPhone2()!=null) {
			client.getPhones().add(obj.getPhone2());
		}
		if (obj.getPhone3()!=null) {
			client.getPhones().add(obj.getPhone3());
		}
		return client;
	}
	
	private void updateData(Client newObj, Client obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

}
