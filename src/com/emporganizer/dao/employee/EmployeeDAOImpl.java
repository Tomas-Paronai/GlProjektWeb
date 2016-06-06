package com.emporganizer.dao.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.emporganizer.dao.items.ItemDAO;
import com.emporganizer.models.employee.Address;
import com.emporganizer.models.employee.Contact;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmployeePresent;
import com.emporganizer.models.employee.EmploymentDetail;
import com.emporganizer.models.items.Contract;
import com.emporganizer.models.items.Position;

public class EmployeeDAOImpl implements EmployeeDAO{
	
	private JdbcTemplate jdbc;
	
	@Autowired
	private ShiftDAO shiftDAO;
	
	@Autowired
	private ItemDAO itemDAO;
	
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
	public List<Employee> getEmployeeList(String search) {
		String sql = "SELECT * FROM `employee` "
				+ "INNER JOIN `address` ON employee.EmployeeID=address.EmployeeID "
				+ "INNER JOIN `contact` ON employee.EmployeeID=contact.EmployeeID "
				+ "INNER JOIN `employment_detail` ON employee.EmployeeID=employment_detail.EmployeeID "
				+ "INNER JOIN `position` ON employment_detail.PositionID=position.PositionID "
				+ "INNER JOIN `contract` ON employment_detail.ContractID=contract.ContractID ";
		
		
		return null;
	}

	@Override
	public List<Employee> getSortedList(String criteria, String search, boolean desc) {
		String sql = "SELECT * FROM `employee` "
				+ "INNER JOIN `address` ON employee.EmployeeID=address.EmployeeID "
				+ "INNER JOIN `contact` ON employee.EmployeeID=contact.EmployeeID "
				+ "INNER JOIN `employment_detail` ON employee.EmployeeID=employment_detail.EmployeeID "
				+ "INNER JOIN `position` ON employment_detail.PositionID=position.PositionID "
				+ "INNER JOIN `contract` ON employment_detail.ContractID=contract.ContractID";
		
		if(search != null && search != ""){
			sql += " WHERE";
			sql += " `firstname` LIKE '%" + search + "%' OR";
			sql += " `surname` LIKE '%" + search + "%' OR";
			sql += " `email` LIKE '%" + search + "%' OR";
			sql += " `phone` LIKE '%" + search + "%' OR";
			sql += " `city` LIKE '%" + search + "%' OR";
			sql += " `country` LIKE '%" + search + "%' OR";
			sql += " `street` LIKE '%" + search + "%' OR";
			sql += " `postcode` LIKE '%" + search + "%' OR";
			sql += " `position_name` LIKE '%" + search + "%' OR";
			sql += " `contract_name` LIKE '%" + search + "%'";
		}
		
		if(criteria != null && !criteria.equals("")){
			sql+=" ORDER BY `" + criteria + "`"+ (desc ? " desc" : "");
		}		
		
		return jdbc.query(sql, new EmployeeRowMapper());
	}
	
	@Override
	public List<Employee> getEmployeeByListId(List<Integer> empIds) {
		String sql = "SELECT * FROM `employee` "
				+ "INNER JOIN `address` ON employee.EmployeeID=address.EmployeeID "
				+ "INNER JOIN `contact` ON employee.EmployeeID=contact.EmployeeID "
				+ "INNER JOIN `employment_detail` ON employee.EmployeeID=employment_detail.EmployeeID "
				+ "INNER JOIN `position` ON employment_detail.PositionID=position.PositionID "
				+ "INNER JOIN `contract` ON employment_detail.ContractID=contract.ContractID WHERE ";
		
		for(int i = 0; i < empIds.size(); i++){
			sql += "employee.EmployeeID="+empIds.get(i);
			if(i + 1 < empIds.size()){
				sql += " or ";
			}
		}
		return jdbc.query(sql, new EmployeeRowMapper());
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
	public Employee getLastEmployee() {
		String sql = "SELECT * FROM employee ORDER BY EmployeeID desc LIMIT 1";
		Employee tmpEmployee = jdbc.queryForObject(sql, new EmployeeRowMapper());
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
	public void insertEmployee(Employee emp) {
		String mainSql = "INSERT INTO employee (`firstname`,`surname`,`gender`,`birthdate`) VALUES (?,?,?,?)";
		String contactSql = "INSERT INTO contact VALUES (?,?,?)";
		String addressSql = "INSERT INTO address VALUES (?,?,?,?,?)";
		String detailSql = "INSERT INTO employment_detail VALUES (?,?,?,?,?)";
		
		jdbc.update(mainSql, new Object[]{emp.getFirstName(),emp.getLastName(),emp.getSex().getVal(),emp.getDob()});			
		int id = getLastEmployee().getId();
		
		Address address = emp.getAddress();
		jdbc.update(addressSql, new Object[]{id,address.getCountry(),address.getCity(),address.getStreet(),address.getPostCode()});
		
		Contact contact = emp.getContact();
		jdbc.update(contactSql,new Object[]{id,contact.getPhone(),contact.getEmail()});
		
		EmploymentDetail detail = emp.getDetail();
		Position position = (Position) itemDAO.getItem("position", detail.getPosition());
		int posId = 0;
		if(position == null){
			itemDAO.insertItem(detail.getPosition(), "position");
			posId = itemDAO.getLastItem("position").getId();				
		}
		else{
			posId = position.getId();
		}
		Contract contract = (Contract) itemDAO.getItem("contract", detail.getContract());
		int conId = 0;
		if(contract == null){
			itemDAO.insertItem(detail.getContract(), "contract");
			conId = itemDAO.getLastItem("contract").getId();
		}
		else{
			conId = contract.getId();
		}
		jdbc.update(detailSql,new Object[]{id,posId,conId,detail.getSalary(),detail.getWorkSince()});
	}
	

	private void loadShifts(Employee employee){
		employee.setPastShifts(shiftDAO.getShifts(employee.getId()));
	}
	
	@Transactional
	@Override
	public void insertEmployee(List<Employee> employees) {		
		for(Employee emp : employees){
			insertEmployee(emp);
		}		
	}

	

	
	
}
