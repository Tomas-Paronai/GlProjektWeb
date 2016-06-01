package com.emporganizer.controllers;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emporganizer.dao.employee.EmployeeDAO;
import com.emporganizer.dao.employee.ShiftDAO;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmployeePresent;
import com.emporganizer.models.employee.Shift;

@Controller
public class HomePageController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	ShiftDAO shiftDAO;
	
	@RequestMapping(value = "/employeeDetail", method = RequestMethod.GET)
	public @ResponseBody Employee employeeDetail(@RequestParam(value = "id", required = true) int id){
		Employee tmpEmployee = employeeDAO.getEmployeeById(id);
		return tmpEmployee;
	}
	
	@RequestMapping(value = "/employeeStatus", method = RequestMethod.GET)
	public @ResponseBody List<EmployeePresent> employeeStatus(){
		return employeeDAO.getPresentEmployees();
	}
	
	@RequestMapping(value = "employeeShifts", method = RequestMethod.GET)
	public String getEmployeeShifts(@RequestParam(value = "id", required = true) int id, ModelMap model){
		model.addAttribute("shifts", shiftDAO.getShifts(id));
		return "pages/dialog/pastShifts";
	}
	
	@ExceptionHandler({SQLException.class,DataAccessException.class})
	public ModelAndView getSQLExceptionPage(HttpServletRequest req, Exception exception){
		ModelAndView model = new ModelAndView("pages/errorPage");
		model.addObject("message", "Database is currently unavailable. Please try again later or contact support.");
		model.addObject("exception", exception);
		return model;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView getExceptionPage(HttpServletRequest req, Exception exception){
		ModelAndView model = new ModelAndView("pages/errorPage");
		model.addObject("message", "Request error occured. Please try again later or contact support.");
		model.addObject("exception", exception);
		return model;
	}
}
