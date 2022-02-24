package com.sc.domain.enums;

public enum ClientType {
	
	PHYSICAL(1, "physical person"),
	LEGAL(2, "legal person");
	
	private int cod;
	private String description;
	
	private ClientType(Integer cod, String description) {
		this.cod=cod;
		this.description=description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static ClientType toEnum(Integer cod) {
		if (cod==null) {
			return null;
		}
		for (ClientType x : ClientType.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Id "+cod);
	}

}
