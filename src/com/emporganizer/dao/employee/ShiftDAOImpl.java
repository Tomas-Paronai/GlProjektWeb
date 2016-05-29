package com.emporganizer.dao.employee;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.emporganizer.models.employee.Shift;

public class ShiftDAOImpl implements ShiftDAO{
	
	private JdbcTemplate jdbc;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);		
	}

	@Override
	public List<Shift> getShifts(int employeeId) {
		String sql = "SELECT * FROM past_shift WHERE EmployeeID=?";
		return jdbc.query(sql, new Object[]{employeeId}, new ShiftRowMapper());
	}

}
