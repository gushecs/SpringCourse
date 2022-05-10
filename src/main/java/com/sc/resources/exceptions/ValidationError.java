package com.sc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> fieldMessage = new ArrayList<>();

	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(status, error, timestamp, message, path);
	}

	public List<FieldMessage> getErrors() {
		return fieldMessage;
	}

	public void addError(String fieldName, String message) {
		fieldMessage.add(new FieldMessage(fieldName,message));
	}

}
