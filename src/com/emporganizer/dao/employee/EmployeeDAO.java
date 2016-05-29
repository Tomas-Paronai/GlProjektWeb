package com.emporganizer.dao.employee;

import java.util.List;

import com.emporganizer.dao.RootDAO;
import com.emporganizer.models.employee.Employee;

public interface EmployeeDAO extends RootDAO{
	public List<Employee> getEmployeeList();
	public Employee getEmployeeById(int employeeId);
	public void deleteEmployee(int employeeId);
	public void updateEmployee(Employee upEmployee);
	public void insertEmployee(Employee newEmployee);
}