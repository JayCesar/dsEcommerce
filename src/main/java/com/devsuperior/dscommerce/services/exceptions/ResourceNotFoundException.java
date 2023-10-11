package com.devsuperior.dscommerce.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	// RuntimeException não exige o tryCatch, mas o Exception sim.
	
	public ResourceNotFoundException(String msg) {
		super(msg);
	}
}
