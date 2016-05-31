package com.emporganizer.dao.employee;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.emporganizer.models.employee.Address;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmploymentDetail;

public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		EmploymentDetail detail = new EmploymentDetail(rs.getString("position_name"), 
													   rs.getString("contract_name"), 
													   rs.getFloat("Salary_per_hour"), 
													   rs.getDate("Start_work"));
		
		Address address = new Address(rs.getString("Country"),
									  rs.getString("City"),
									  rs.getString("Street"),
									  rs.getString("PostCode"));
		
		
		Employee employee = new Employee(rs.getInt("EmployeeID"),
										 rs.getString("FirstName"),
										 rs.getString("Surname"),
										 rs.getString("Gender"),
										 rs.getDate("BirthDate"),
										 rs.getString("Phone"),
										 rs.getString("Email"),
										 address,
										 detail);
				
		return employee;
	}

}
