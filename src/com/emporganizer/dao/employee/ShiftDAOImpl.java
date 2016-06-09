package com.emporganizer.dao.employee;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

	@Override
	public List<Shift> getShifts(int employeeId, int period) {
		String sql = "SELECT * FROM past_shift WHERE EmployeeID=?";	
		if(period == 0){			 
			 return jdbc.query(sql, new Object[]{employeeId}, new ShiftRowMapper());
		}
		else{
			sql += "AND Exit_time<?";
		}
		List<Shift> result = jdbc.query(sql, new Object[]{employeeId, getDatePeriod(period)}, new ShiftRowMapper());
		return result;
	}

	@Override
	public Date getDatePeriod(int period) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date utilDate = new java.util.Date();
		String s1 = format.format(utilDate);
		
		Calendar calendar = Calendar.getInstance();	
		calendar.setTime(utilDate);
		calendar.add(Calendar.DAY_OF_YEAR, DAYS[period]);
		
		java.util.Date utilDateResult = calendar.getTime();
		String s2 = format.format(utilDateResult);
		return new Date(utilDateResult.getTime());
	}

}
