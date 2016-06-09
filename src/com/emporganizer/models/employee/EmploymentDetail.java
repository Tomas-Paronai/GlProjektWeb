package com.emporganizer.models.employee;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EmploymentDetail {
	private String position;
	private int positionID;
	private String contract;
	private int contractID;
	private float salary;
	private Date workSince;
	
	
	
	/**
	 * 
	 */
	public EmploymentDetail() {
		
	}

	public EmploymentDetail(String position, String contract, float salary, Date workSince) {
		super();
		this.position = position;
		this.contract = contract;
		this.salary = salary;
		this.workSince = workSince;
		
	}
	
	public EmploymentDetail(String position, String contract, float salary, String workSince) {
		super();
		this.position = position;
		this.contract = contract;
		this.salary = salary;
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.workSince =  new Date(((java.util.Date)df.parse(workSince)).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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

	public int getPositionID() {
		return positionID;
	}

	public void setPositionID(int positionID) {
		this.positionID = positionID;
	}

	public int getContractID() {
		return contractID;
	}

	public void setContractID(int contractID) {
		this.contractID = contractID;
	}	
	
	
}
