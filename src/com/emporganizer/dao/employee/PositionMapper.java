package com.emporganizer.dao.employee;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.emporganizer.models.employee.PositionType;



public class PositionMapper  implements RowMapper<PositionType> {

	@Override
	public PositionType mapRow(ResultSet rs, int rowNum) throws SQLException {
		PositionType position;
		position = new PositionType(rs.getInt("PositionID"),
				rs.getString("position_name"));
		return position;
	}

	

}
