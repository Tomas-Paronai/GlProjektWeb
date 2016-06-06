package com.emporganizer.dao.employee;

import java.util.List;

import com.emporganizer.dao.RootDAO;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmployeePresent;

public interface EmployeeDAO extends RootDAO{
	public List<Employee> getEmployeeList();
	public List<Employee> getSortedList(String criteria, boolean desc);
	public List<Employee> getEmployeeByListId(List<Integer> empIds);
	public List<EmployeePresent> getPresentEmployees();
	public Employee getEmployeeById(int employeeId);
	public Employee getLastEmployee();
	public void deleteEmployee(int employeeId);
	public void updateEmployee(Employee upEmployee);
	public void insertEmployee(Employee newEmployee);
	public void insertEmployee(List<Employee> employees);
}
