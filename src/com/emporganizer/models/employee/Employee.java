package com.emporganizer.models.employee;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import com.emporganizer.api.beans.EmployeeBean;

public class Employee {
	private int id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private Date dob;
	private Contact contact;
	private Address address;
	private EmploymentDetail detail;
	private List<Shift> pastShifts;
	
	public static Employee parseEmployee(EmployeeBean bean){
		Address address = new Address(bean.getCountry(),bean.getCity(),bean.getStreet(),bean.getPostcode());
		Contact contact = new Contact(bean.getPhone(),bean.getEmail());
		EmploymentDetail detail = new EmploymentDetail(bean.getPosition(),bean.getContract(),bean.getSalary(),bean.getEmployedsince());
		Employee emp = new Employee(bean.getId(),bean.getFirtsName(),bean.getLastName(),bean.getSex(),bean.getDob(),contact,address,detail);
		return emp;
	}
	
	public Employee(){
		
	}
	
	public Employee(int id, String firstName, String lastName, String sex, Date dob, Contact contact, Address address,
			EmploymentDetail detail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.contact = contact;
		this.address = address;
		this.detail = detail;
		
		if(sex.equals("Female")){
			this.sex = Sex.FEMALE;
		}
		else{
			this.sex = Sex.MALE;
		}
	}
	
	public Employee(int id, String firstName, String lastName, String sex, String dob, Contact contact, Address address,
			EmploymentDetail detail) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.contact = contact;
		this.address = address;
		this.detail = detail;
		
		if(sex.equals("Female")){
			this.sex = Sex.FEMALE;
		}
		else{
			this.sex = Sex.MALE;
		}
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.dob =  new Date(((java.util.Date)df.parse(dob)).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
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
	
	public String getName(){
		return firstName + " " + lastName;
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + "]";
	}	
	
}
