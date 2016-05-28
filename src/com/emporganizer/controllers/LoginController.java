package com.emporganizer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.emporganizer.dao.employee.EmployeeDAO;

@Controller
public class LoginController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@RequestMapping("/home")
	public ModelAndView getHomePage(){
		ModelAndView model = new ModelAndView("pages/home");
		
		model.addObject("employees",employeeDAO.getEmployeeList());
		
		return model;
	}
}
