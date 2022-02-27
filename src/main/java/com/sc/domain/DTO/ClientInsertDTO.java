package com.sc.domain.DTO;

import java.io.Serializable;

public class ClientInsertDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String email;
	private String cpf_cnpj;
	private Integer clientType;
	
	private String street;
	private String number;
	private String complement;
	private String district;
	private String postalcode;
	private Integer cityId;
	
	private String phone1;
	private String phone2;
	private String phone3;
	
	public ClientInsertDTO() {}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getCpf_cnpj() {
		return cpf_cnpj;
	}

	public Integer getClientType() {
		return clientType;
	}

	public String getStreet() {
		return street;
	}

	public String getNumber() {
		return number;
	}

	public String getComplement() {
		return complement;
	}

	public String getDistrict() {
		return district;
	}

	public String getPostalcode() {
		return postalcode;
	}

	public Integer getCityId() {
		return cityId;
	}

	public String getPhone1() {
		return phone1;
	}

	public String getPhone2() {
		return phone2;
	}

	public String getPhone3() {
		return phone3;
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

	public void setClientType(Integer clientType) {
		this.clientType = clientType;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public void setPostalcode(String postalcode) {
		this.postalcode = postalcode;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public void setPhone1(String phone1) {
		this.phone1 = phone1;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public void setPhone3(String phone3) {
		this.phone3 = phone3;
	}

}
