package com.sc.domain.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.sc.domain.Client;
import com.sc.services.validation.ClientUpdate;

@ClientUpdate
public class ClientDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Obrigatory field: name.")
	@Length(min=5, max=120, message="it must have between 5 and 120 characters.")
	private String name;
	
	@NotEmpty(message = "Obrigatory field: e-mail.")
	@Email(message = "Invalid e-mail.")
	private String email;
	
	public ClientDTO() {}
	
	public ClientDTO(Client obj) {
		this.id=obj.getId();
		this.name=obj.getName();	
		this.email=obj.getEmail();
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
