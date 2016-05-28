package com.emporganizer.models.employee;

public enum Sex {
	
	FEMALE("Female"),
	MALE("Male");
	
	private final String value;
	
	Sex(final String value){
		this.value = value;
	}
	
	public String getVal(){
		return value;
	}
}
