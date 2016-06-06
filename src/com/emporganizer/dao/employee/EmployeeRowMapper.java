package com.emporganizer.dao.employee;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.emporganizer.models.employee.Address;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmploymentDetail;

public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmploymentDetail detail = null;
		if(hasColumn(rs, "position_name") && hasColumn(rs, "contract_name") && hasColumn(rs, "Salary_per_hour") && hasColumn(rs, "Start_work")){
			detail = new EmploymentDetail(rs.getString("position_name"), 
					   					  rs.getString("contract_name"), 
					                      rs.getFloat("Salary_per_hour"), 
					                      rs.getDate("Start_work"));
		}
		
		Address address  = null;
		if(hasColumn(rs, "Country") && hasColumn(rs, "City") && hasColumn(rs, "Street") && hasColumn(rs, "PostCode")){
			address = new Address(rs.getString("Country"),
					  			  rs.getString("City"),
					  			  rs.getString("Street"),
					  			  rs.getString("PostCode"));
		}
		
		
		
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
	
	public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columns = rsmd.getColumnCount();
	    for (int x = 1; x <= columns; x++) {
	        if (columnName.equals(rsmd.getColumnName(x))) {
	            return true;
	        }
	    }
	    return false;
	}
}
