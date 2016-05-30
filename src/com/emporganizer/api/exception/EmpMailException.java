package com.emporganizer.api.exception;

public class EmpMailException extends RuntimeException{
	
	private String message;

	public EmpMailException(String message) {
		super();
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
