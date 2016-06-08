package com.emporganizer.dao.employee;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.emporganizer.models.employee.Address;
import com.emporganizer.models.employee.Contact;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmploymentDetail;

public class EmployeeRowMapper implements RowMapper<Employee>{

	@Override
	public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		EmploymentDetail detail = null;
		if(hasColumn(rs, "Position_name") && hasColumn(rs, "Contract_name") && hasColumn(rs, "Salary_per_hour") && hasColumn(rs, "Start_work")){
			detail = new EmploymentDetail(rs.getString("Position_name"), 
					   					  rs.getString("Contract_name"), 
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
		
		Contact contact = null;
		if(hasColumn(rs, "Phone") && hasColumn(rs, "Email")){
			contact = new Contact(rs.getString("Phone"),
								  rs.getString("Email"));
		}
		
		Employee employee = new Employee(rs.getInt("EmployeeID"),
										 rs.getString("FirstName"),
										 rs.getString("Surname"),
										 rs.getString("Gender"),
										 rs.getDate("BirthDate"),
										 contact,
										 address,
										 detail);
				
		return employee;
	}
	
	public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int columns = rsmd.getColumnCount();
	    for (int x = 1; x <= columns; x++) {
	    	String name = rsmd.getColumnName(x);
	        if (columnName.equals(rsmd.getColumnName(x))) {
	            return true;
	        }
	    }
	    return false;
	}
}
