package com.emporganizer.models.employee;

import java.sql.Date;

public class EmployeeHelper {
	private int id;
	private String firstName;
	private String lastName;
	private Sex sex;
	private Date dob;
	private String country;
	private String city;
	private String street;
	private String postCode;
	private String phone;
	private String email;
	private String position;
	private int positionID;
	private String contract;
	private int contractID;
	private float salary;
	private Date workSince;
	
	
	
	
	
	
	public EmployeeHelper(){
		
	}
	public EmployeeHelper(Employee employee) {
		
		this.id = employee.getId();
		this.firstName = employee.getFirstName();
		this.lastName = employee.getLastName();
		this.sex = employee.getSex();
		this.dob = employee.getDob();
		this.country = employee.getAddress().getCountry();
		this.city = employee.getAddress().getCity();
		this.street = employee.getAddress().getStreet();
		this.postCode = employee.getAddress().getPostCode();
		this.phone = employee.getContact().getPhone();
		this.email =  employee.getContact().getEmail();
		this.position = employee.getDetail().getPosition();
		this.contract = employee.getDetail().getContract();
		this.salary = employee.getDetail().getSalary();
		this.workSince = employee.getDetail().getWorkSince();	
	}
	
	
	


	/**
	 * @param id
	 * @param firstName
	 * @param lastName
	 * @param sex
	 * @param dob
	 * @param country
	 * @param city
	 * @param street
	 * @param postCode
	 * @param phone
	 * @param email
	 * @param position
	 * @param positionID
	 * @param contract
	 * @param contractID
	 * @param salary
	 * @param workSince
	 */
	public EmployeeHelper(int id, String firstName, String lastName, Sex sex, Date dob, String country, String city,
			String street, String postCode, String phone, String email, String position, int positionID,
			String contract, int contractID, float salary, Date workSince) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.sex = sex;
		this.dob = dob;
		this.country = country;
		this.city = city;
		this.street = street;
		this.postCode = postCode;
		this.phone = phone;
		this.email = email;
		this.position = position;
		this.positionID = positionID;
		this.contract = contract;
		this.contractID = contractID;
		this.salary = salary;
		this.workSince = workSince;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getPostCode() {
		return postCode;
	}
	public void setPostCode(String postCode) {
		this.postCode = postCode;
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
