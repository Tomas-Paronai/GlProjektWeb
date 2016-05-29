package com.emporganizer.dao.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmployeePresent;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	private ShiftDAO shiftDAO;
	
	@Override
	public void setDataSource(DataSource dataSource) {
		jdbc = new JdbcTemplate(dataSource);		
	}

	@Override
	public List<Employee> getEmployeeList() {
		String sql = "SELECT * FROM `employee` "
				+ "INNER JOIN `address` ON employee.EmployeeID=address.EmployeeID "
				+ "INNER JOIN `contact` ON employee.EmployeeID=contact.EmployeeID "
				+ "INNER JOIN `employment_detail` ON employee.EmployeeID=employment_detail.EmployeeID "
				+ "INNER JOIN `position` ON employment_detail.PositionID=position.PositionID "
				+ "INNER JOIN `contract` ON employment_detail.ContractID=contract.ContractID ";
		
		List<Employee> employees = jdbc.query(sql, new EmployeeRowMapper());		
		for(Employee tmpEmployee : employees){
			loadShifts(tmpEmployee);
		}
		
		return employees;
	}
	
	@Override
	public List<EmployeePresent> getPresentEmployees() {
		String sql = "SELECT `EmployeeID`,`Check_time` FROM work_shift WHERE `Type`='IN'";
		return jdbc.query(sql, new RowMapper<EmployeePresent>(){

			@Override
			public EmployeePresent mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new EmployeePresent(rs.getInt("EmployeeID"),rs.getTimestamp("Check_time"));
			}
			
		});
	}
	
	@Override
	public Employee getEmployeeById(int employeeId) {
		String sql = "SELECT * FROM `employee` "
					+ "INNER JOIN `address` ON employee.EmployeeID=address.EmployeeID "
					+ "INNER JOIN `contact` ON employee.EmployeeID=contact.EmployeeID "
					+ "INNER JOIN `employment_detail` ON employee.EmployeeID=employment_detail.EmployeeID "
					+ "INNER JOIN `position` ON employment_detail.PositionID=position.PositionID "
					+ "INNER JOIN `contract` ON employment_detail.ContractID=contract.ContractID "
					+ "WHERE employee.EmployeeID=?";
		
		Employee tmpEmployee = jdbc.queryForObject(sql, new Object[]{employeeId}, new EmployeeRowMapper());
		loadShifts(tmpEmployee);
		
		return tmpEmployee;
	}

	@Override
	public void deleteEmployee(int employeeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateEmployee(Employee upEmployee) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertEmployee(Employee newEmployee) {
		// TODO Auto-generated method stub
		
	}

	private void loadShifts(Employee employee){
		employee.setPastShifts(shiftDAO.getShifts(employee.getId()));
	}

	
}
