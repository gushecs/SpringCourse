package com.sc.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sc.domain.enums.ClientType;

@Entity
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpf_cnpj;
	private Integer clientType;
	
	@OneToMany (mappedBy = "client")
	@JsonManagedReference
	private List<Address> addresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable (name = "phone")
	private Set<String> phones = new HashSet<>();
	
	@OneToMany (mappedBy = "client")
	private List<OrderClass> orders = new ArrayList<>();
	
	public Client() {}

	public Client(Integer id, String name, String email, String cpf_cnpj, ClientType clientType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpf_cnpj = cpf_cnpj;
		this.clientType = clientType.getCod();
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public ClientType getClientType() {
		return ClientType.toEnum(clientType);
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public Set<String> getPhones() {
		return phones;
	}

	public List<OrderClass> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderClass> orders) {
		this.orders = orders;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCpf_cnpj(String cpf_cnpj) {
		this.cpf_cnpj = cpf_cnpj;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType.getCod();
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public void setPhones(Set<String> phones) {
		this.phones = phones;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	};

}
