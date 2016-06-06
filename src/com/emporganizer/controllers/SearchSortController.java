package com.emporganizer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.emporganizer.dao.employee.EmployeeDAO;
import com.emporganizer.dao.employee.ShiftDAO;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmployeePresent;

@Controller
public class SearchSortController {
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	ShiftDAO shiftDAO;
	
	@RequestMapping("/sort")
	public @ResponseBody List<Employee> getSortedList(@RequestParam(value = "sort", required = true) String head,
													  @RequestParam(value = "desc", required = true) String desc,
													  @RequestParam(value = "search", required = false) String search){
		
		boolean descending = (desc.equals("true") ? true : false);
		if(head.equals("At work")){
			List<Employee> employees;
			if(search != null && !search.equals("")){
				employees = employeeDAO.getEmployeeList(search);
			}
			employees = employeeDAO.getEmployeeList();
			List<EmployeePresent> presentFolk = employeeDAO.getPresentEmployees();
			
			List<Employee> present = new ArrayList<>();
			for(EmployeePresent curPresent : presentFolk){
				present.add(employeeDAO.getEmployeeById(curPresent.getId()));
			}
			
			List<Employee> remove = new ArrayList<>();
			for(Employee presentEmp : present){
				for(Employee tmpEmp : employees){
					if(presentEmp.getId() == tmpEmp.getId()){
						employees.remove(tmpEmp);
						break;
					}
				}
				remove.add(presentEmp);
			}
			if(remove.size() > 0){
				for(Employee tmpRemove : remove){
					for(Employee presentEmp : present){
						if(tmpRemove.getId() == presentEmp.getId()){
							present.remove(presentEmp);
							break;
						}
					}
				}
			}
			
			List<Employee> result = new ArrayList<>();
			if(descending){
				result.addAll(present);
				result.addAll(employees);
				return result;
			}
			result.addAll(employees);
			result.addAll(present);
			return result;
		}
		
		return employeeDAO.getSortedList(head, search, descending);
	}
	
	
}
