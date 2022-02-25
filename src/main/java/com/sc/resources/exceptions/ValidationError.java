package com.sc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> fieldMessage = new ArrayList<>();

	public ValidationError(Integer status, String msg, Long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return fieldMessage;
	}

	public void addError(String fieldName, String message) {
		fieldMessage.add(new FieldMessage(fieldName,message));
	}

}
