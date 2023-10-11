package com.devsuperior.dscommerce.services.exceptions;

public class DatabaseException extends RuntimeException{
	// RuntimeException não exige o tryCatch, mas o Exception sim.
	
	public DatabaseException(String msg) {
		super(msg);
	}
}
