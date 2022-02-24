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

import com.sc.domain.enums.ClientType;

@Entity
public class Client implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String email;
	private String cpfCnpj;
	private Integer clientType;
	
	@OneToMany (mappedBy = "client")
	private List<Adress> adresses = new ArrayList<>();
	
	@ElementCollection
	@CollectionTable (name = "phone")
	private Set<String> phones = new HashSet<>();
	
	public Client() {}

	public Client(Integer id, String name, String email, String cpfCnpj, ClientType clientType) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.cpfCnpj = cpfCnpj;
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

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public ClientType getClientType() {
		return ClientType.toEnum(clientType);
	}

	public List<Adress> getAdresses() {
		return adresses;
	}

	public Set<String> getPhones() {
		return phones;
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

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public void setClientType(ClientType clientType) {
		this.clientType = clientType.getCod();
	}

	public void setAdresses(List<Adress> adresses) {
		this.adresses = adresses;
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
