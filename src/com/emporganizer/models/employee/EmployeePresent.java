package com.emporganizer.models.employee;

import java.sql.Timestamp;

public class EmployeePresent {
	private int id;
	private Timestamp entered;	
	
	public EmployeePresent(int id, Timestamp entered) {
		super();
		this.id = id;
		this.entered = entered;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Timestamp getEntered() {
		return entered;
	}
	public void setEntered(Timestamp entered) {
		this.entered = entered;
	}
	
}
