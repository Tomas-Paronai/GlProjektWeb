package com.emporganizer.models.employee;

import java.sql.Date;

public class EmploymentDetail {
	private String position;
	private String contract;
	private float salary;
	private Date workSince;
	
	public EmploymentDetail(String position, String contract, float salary, Date workSince) {
		super();
		this.position = position;
		this.contract = contract;
		this.salary = salary;
		this.workSince = workSince;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getContract() {
		return contract;
	}

	public void setContract(String contract) {
		this.contract = contract;
	}

	public float getSalary() {
		return salary;
	}

	public void setSalary(float salary) {
		this.salary = salary;
	}

	public Date getWorkSince() {
		return workSince;
	}

	public void setWorkSince(Date workSince) {
		this.workSince = workSince;
	}	
	
}
