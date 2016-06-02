package com.emporganizer.controllers;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@ControllerAdvice
public class ErrorController {
	
	@ExceptionHandler(CommunicationsException.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("pages/errorPage");
		model.addObject("message", "CommunicationsException ...please check if database is available.");
		model.addObject("esception", ex.getMessage());
		return model;

	}
}
