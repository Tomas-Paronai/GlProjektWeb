package com.emporganizer.dao.employee;

import java.util.List;


import com.emporganizer.dao.RootDAO;
import com.emporganizer.models.employee.Address;
import com.emporganizer.models.employee.ContractType;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmployeeHelper;
import com.emporganizer.models.employee.EmployeePresent;
import com.emporganizer.models.employee.EmploymentDetail;
import com.emporganizer.models.employee.PositionType;



public interface EmployeeDAO extends RootDAO{
	public List<Employee> getEmployeeList();
	public List<Employee> getEmployeeList(String search);
	public List<Employee> getSortedList(String criteria, String search ,boolean desc);
	public List<Employee> getEmployeeByListId(List<Integer> empIds);
	public List<EmployeePresent> getPresentEmployees();
	public Employee getEmployeeById(int employeeId);
	public Employee getLastEmployee();
	public void deleteEmployee(int employeeId);
	public void updateEmployee(EmployeeHelper employee);
	public void updateAddress(EmployeeHelper epmployee);
	public void updateContact(EmployeeHelper epmployee);
	public void updateDetail(EmployeeHelper epmployee);
	public void insertEmployee(Employee newEmployee);
	public void insertEmployee(List<Employee> employees);
	public List<ContractType> getListOfContracts();
	public List<PositionType> getListOfPositions();
	
}
