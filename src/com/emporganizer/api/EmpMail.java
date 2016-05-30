package com.emporganizer.api;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.emporganizer.api.exception.EmpMailException;



public class EmpMail {
	
	JavaMailSender mailSender;
	
	private StringBuilder builder;
	private MimeMessage mimeMessage;
	private MimeMessageHelper helper;
	
	private String[] address;
	private String subject;
	
	public void setMailSender(JavaMailSender mailSender){
		this.mailSender = mailSender;
	}
	
	public void sendMail(String subject, String htmlMessage, String... address) throws MessagingException{
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		htmlMessage += "<br><p>Sincerely, <br />Employee organizer Admin Team</p>"
					+ "<br><small>Don't answer on this email.</small>";
		mimeMessage.setContent(htmlMessage, "text/html");
		helper.setTo(address);
		helper.setSubject(subject);
		helper.setFrom("mail.gl.ke@gmail.com");
		mailSender.send(mimeMessage);
	}
	
	public void newMessage(String subject, String htmlMessage, String... address) throws MessagingException{
		if(address == null){
			new EmpMailException("String address cannot be null.");
		}
		
		if(subject == null){
			new EmpMailException("String subject cannot be null.");
		}
			
		builder = new StringBuilder();
		mimeMessage = mailSender.createMimeMessage();		
		helper = new MimeMessageHelper(mimeMessage, false, "utf-8");
		
		if(htmlMessage != null){
			builder.append(htmlMessage);
		}		
		if(address != null && address.length > 0){
			this.address = address;
		}
		if(subject != null){
			this.subject = subject;
		}				
		helper.setFrom("emp.organizer@gmail.com");
	}
	
	public void addAddress(String... address){
		if(address != null){
			if(this.address == null){
				this.address = address;
			}
			this.address = concat(this.address,address);
		}		
	}
	
	public void appendText(String htmlText){
		if(builder != null){
			builder.append(htmlText);
		}		
		else{
			new EmpMailException("You must use newMessage() before appending.");
		}
	}
	
	public void sendMail() throws MessagingException{
		if(address == null || address.length <= 0){
			new EmpMailException("You must set atleast one recipient");
		}
		if(subject == null){
			this.subject = "No subject";
		}
		
		if(mimeMessage != null && builder != null){
			
			mimeMessage.setContent(builder.toString(), "text/html");
			helper.setTo(address);
			helper.setSubject(subject);
			
			mailSender.send(mimeMessage);
			
			mimeMessage = null;
			builder = null;
			helper = null;
			
		}		
		else{
			new EmpMailException("You must use newMessage() before appending.");
		}
	}
	
	private String[] concat(String[] one, String[] two){
		String[] result;
		if(one != null && two != null){
			result = new String[one.length + two.length];
		}
		else if(one != null){
			result = new String[one.length];
		}
		else if(two != null){
			result = new String[two.length];
		}
		else{
			result = new String[0];
		}
		
		return result;
	}
}
