package com.emporganizer.dao.items;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import com.emporganizer.models.items.DBItem;

public class ItemDAOImpl implements ItemDAO{
	
	private JdbcTemplate jdbc;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);
	}

	@Override
	public List<DBItem> getItems(String table) {
		String sql = "SELECT * FROM `" + table + "`";
		return jdbc.query(sql, new ItemRowMapper(table));
	}

	@Override
	public DBItem getItem(String table, int id) {
		String sql = "SELECT * FROM `" + table + "` WHERE " + table + "ID=?";
		
		return jdbc.queryForObject(sql, new Object[]{id}, new ItemRowMapper(table));
	}	

	@Override
	public DBItem getItem(String table, String name) {
		String sql = "SELECT * FROM `" + table + "` WHERE " + table + "_name=?";
		List<DBItem> result = jdbc.query(sql, new Object[]{name}, new ItemRowMapper(table));
		if(result.size() >= 1){
			return result.get(0);
		}
		return null;
	}
	
	@Override
	public DBItem getLastItem(String table) {
		String sql = "SELECT * FROM " + table + " ORDER BY " + table + "ID desc LIMIT 1";
		return jdbc.queryForObject(sql, new ItemRowMapper(table));
	}
	
	@Override
	public void insertItem(String value, String table) {
		String sql = "INSERT INTO `" + table + "` (`" + table + "_name`) VALUES (?)";
		jdbc.update(sql, new Object[]{value});
	}

	@Override
	public void updateItem(int id, String value, String table) {
		String sql = "UPDATE `" + table + "` SET " + table + "_name=? WHERE " + table + "ID=?";
		jdbc.update(sql,new Object[]{value,id});
	}

	@Override
	public void deleteItem(int id, String table) {
		String sql = "DELETE FROM " + table + " WHERE " + table + "ID=?";
		jdbc.update(sql, new Object[]{id});
	}


	

	

}
