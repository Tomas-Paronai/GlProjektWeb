package com.emporganizer.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;

import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;

@ControllerAdvice
public class ErrorController {
	
	/*@ExceptionHandler(CommunicationsException.class)
	public ModelAndView handleAllException(Exception ex) {

		ModelAndView model = new ModelAndView("pages/errorPage");
		model.addObject("message", "CommunicationsException ...please check if database is available.");
		model.addObject("esception", ex.getMessage());
		return model;

	}*/
	
	@ExceptionHandler({Exception.class,SQLException.class})
	public ModelAndView handleAllException(Exception ex, HttpServletRequest request) {

		ModelAndView model = new ModelAndView("pages/errorPage");
		model.addObject("message", "We are sorry, but we could not resolve your request. Please try again later or contact the support team.");
		model.addObject("esception", ex.getMessage());
		model.addObject("next", request.getHeader("referer"));
		return model;

	}
}
