package com.questionpro.grocery.exception;

public class ApiException extends RuntimeException{

	private static final long serialVersionUID = -917448146566282670L;

	public ApiException() {
		super();
	}

	public ApiException(String message) {
		super(message);
	}

}
