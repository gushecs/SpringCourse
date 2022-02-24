package com.sc.domain.enums;

public enum PaymentStatus {
	
	WAITING(1, "waiting"),
	PAID(2, "paid"),
	CANCELED(3, "canceled");
	
	private int cod;
	private String description;
	
	private PaymentStatus(Integer cod, String description) {
		this.cod=cod;
		this.description=description;
	}

	public int getCod() {
		return cod;
	}

	public String getDescription() {
		return description;
	}
	
	public static PaymentStatus toEnum(Integer cod) {
		if (cod==null) {
			return null;
		}
		for (PaymentStatus x : PaymentStatus.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		throw new IllegalArgumentException("Invalid Id "+cod);
	}

}
