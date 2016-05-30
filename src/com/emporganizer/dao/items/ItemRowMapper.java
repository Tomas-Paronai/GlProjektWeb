package com.emporganizer.dao.items;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.emporganizer.models.items.*;

public class ItemRowMapper implements RowMapper<DBItem>{
	
	private String table;
	
	public ItemRowMapper(String table) {
		super();
		this.table = table;
	}

	@Override
	public DBItem mapRow(ResultSet rs, int rowNum) throws SQLException {
		if(table.equals("position")){
			return new Position(rs.getInt("PositionID"),rs.getString("position_name"));
		}
		else{
			return new Contract(rs.getInt("ContractID"), rs.getString("contract_name"));
		}
	}

}
