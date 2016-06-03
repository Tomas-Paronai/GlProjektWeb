package com.emporganizer.controllers;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.emporganizer.dao.employee.EmployeeDAO;
import com.emporganizer.dao.login.LoginDao;
import com.emporganizer.models.Login;



@Controller
public class LoginController {
	
	@Autowired
	LoginDao loginDao;
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	 @RequestMapping(value = "/login", method = RequestMethod.GET)  
	    public ModelAndView showform(){  
	        return new ModelAndView("pages/login","command",new Login());  
	    }  
	 @RequestMapping(value="/doLogin", method = RequestMethod.POST)
		public String getLoginfromForm(@ModelAttribute Login login, ModelMap model) throws SQLException{
		System.out.println(login.getPassword() + login.getUserName());
		boolean isValidUser=loginDao.isValidUser(login);
		if(isValidUser==true){
			model.addAttribute("employees",employeeDAO.getEmployeeList());
			return "redirect:/home";
		}
		else{
			return "pages/false";
		}
		
	}
}
