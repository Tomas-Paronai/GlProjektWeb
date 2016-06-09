package com.emporganizer.dao.employee;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.emporganizer.models.employee.ContractType;
import com.emporganizer.models.employee.PositionType;



public class ContractMapper implements RowMapper<ContractType> {

	@Override
	public ContractType mapRow(ResultSet rs, int rowNum) throws SQLException {
		ContractType contract;
		contract = new ContractType(rs.getInt("ContractID"),
				rs.getString("Contract_name"));
		return contract;
	}



}
