package com.emporganizer.models.employee;

import java.sql.Date;
import java.util.List;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private Date dob;
	private String phone;
	private String email;
	private Address address;
	private EmploymentDetail detail;
	private List<Shift> pastShifts;
	
	public Employee(){
		
	}
	
	public Employee(int id, String firstName, String lastName, String sex, Date dob, String phone, String email, Address address,
			EmploymentDetail detail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.detail = detail;
		
		if(sex.equals("Female")){
			this.sex = Sex.FEMALE;
		}
		else{
			this.sex = Sex.MALE;
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}	
	
	public Sex getSex() {
		return sex;
	}

	public void setSex(Sex sex) {
		this.sex = sex;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public EmploymentDetail getDetail() {
		return detail;
	}

	public void setDetail(EmploymentDetail detail) {
		this.detail = detail;
	}

	public List<Shift> getPastShifts() {
		return pastShifts;
	}

	public void setPastShifts(List<Shift> pastShifts) {
		this.pastShifts = pastShifts;
	}	
	
	
}
