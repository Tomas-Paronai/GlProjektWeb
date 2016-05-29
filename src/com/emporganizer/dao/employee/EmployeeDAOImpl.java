package com.emporganizer.dao.employee;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.emporganizer.models.employee.Employee;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private JdbcTemplate jdbc;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);		
	}

	@Override
	public List<Employee> getEmployeeList() {
		String sql = "SELECT * FROM `employee` "
				+ "INNER JOIN `address` ON employee.EmployeeID=address.EmployeeID "
				+ "INNER JOIN `contact` ON employee.EmployeeID=contact.EmployeeID "
				+ "INNER JOIN `employment_detail` ON employee.EmployeeID=employment_detail.EmployeeID "
				+ "INNER JOIN `position` ON employment_detail.PositionID=position.PositionID "
				+ "INNER JOIN `contract` ON employment_detail.ContractID=contract.ContractID "
				+ "WHERE employee.EmployeeID=?";
		
		return jdbc.query(sql, new EmployeeRowMapper());
	}
	
	@Override
	public Employee getEmployeeById(int employeeId) {
		String sql = "SELECT * FROM `employee` "
					+ "INNER JOIN `address` ON employee.EmployeeID=address.EmployeeID "
					+ "INNER JOIN `contact` ON employee.EmployeeID=contact.EmployeeID "
					+ "INNER JOIN `employment_detail` ON employee.EmployeeID=employment_detail.EmployeeID "
					+ "INNER JOIN `position` ON employment_detail.PositionID=position.PositionID "
					+ "INNER JOIN `contract` ON employment_detail.ContractID=contract.ContractID "
					+ "WHERE employee.EmployeeID=?";
		
		return jdbc.queryForObject(sql, new Object[]{employeeId}, new EmployeeRowMapper());
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployee(Employee upEmployee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertEmployee(Employee newEmployee) {
		// TODO Auto-generated method stub
		
	}

	

}