package com.emporganizer.models;

import java.util.List;

public class MailFormBean {
	private String recipents;
	private String subject;
	private String message;
	
	public String getRecipents() {
		return recipents;
	}
	public void setRecipents(String recipents) {
		this.recipents = recipents;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String[] getRecipentList(){
		return recipents.split(";");
	}
}
