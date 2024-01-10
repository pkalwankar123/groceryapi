package com.questionpro.grocery.exception;

public class ResourceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6942445026682660740L;

	Long id;

	public ResourceNotFoundException(Long id) {
		super(String.format("%s Item not found with this %s : %s", id));
		this.id = id;
	}

}
