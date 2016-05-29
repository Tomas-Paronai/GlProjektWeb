package com.emporganizer.dao.employee;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import com.emporganizer.models.employee.Shift;

public class ShiftRowMapper implements RowMapper<Shift> {

	@Override
	public Shift mapRow(ResultSet rs, int rowNum) throws SQLException {
		return new Shift(rs.getTimestamp("Enter_time"),
						 rs.getTimestamp("Exit_time"));
	}

}
