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

import com.emporganizer.api.beans.SelectedEmp;
import com.emporganizer.dao.employee.EmployeeDAO;
import com.emporganizer.dao.employee.ShiftDAO;
import com.emporganizer.models.Login;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmployeePresent;
import com.emporganizer.models.employee.Shift;
import com.mysql.jdbc.exceptions.jdbc4.CommunicationsException;
 
@Controller
public class HomePageController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	ShiftDAO shiftDAO;
	
	@RequestMapping(value = "checkboxList", method = RequestMethod.GET)
	public String getCheckBoxList(@RequestParam(value = "for", required = true) String nextPage, ModelMap model){
		model.addAttribute("employees", employeeDAO.getEmployeeList());
		model.addAttribute("action", nextPage);
		model.addAttribute("hint", getHint(nextPage));
		model.addAttribute("selectedEmp",new SelectedEmp());
		return "/comp/employeeListCheck";
	}
	
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
	
	
	@RequestMapping(value = "showEmployees", method = RequestMethod.GET)
	public ModelAndView showEmployeesToExport() {
		List<Employee> employeeList= employeeDAO.getEmployeeList();
		ModelAndView model = new ModelAndView("pages/exportEmployees");
		 model.addObject("employees",employeeList);
		 return model;
	}
	
	private String getHint(String nextPage) {
		if(nextPage.equals("mailForm")){
			return "Choose employees to send email to.";
		}
		return null;
	}
}
