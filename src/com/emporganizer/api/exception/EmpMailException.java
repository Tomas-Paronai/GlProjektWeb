package com.emporganizer.api.exception;

public class EmpMailException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6787709293339396154L;
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
