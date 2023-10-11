package com.devsuperior.dscommerce.dto;

public class FieldMessage {
	
	private String fileName; // Nome do campo
	private String message;
	
	public FieldMessage(String fileName, String message) {
		this.fileName = fileName;
		this.message = message;
	}

	public String getFileName() {
		return fileName;
	}

	public String getMessage() {
		return message;
	}
	
}
