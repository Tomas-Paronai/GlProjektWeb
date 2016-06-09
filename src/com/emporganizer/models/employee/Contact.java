package com.emporganizer.models.employee;

public class Contact {
	private String phone;
	private String email;
	
	
	
	
	/**
	 * @param phone
	 * @param email
	 */
	public Contact(String phone, String email) {
		super();
		this.phone = phone;
		this.email = email;
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
	
	

}
