package com.emporganizer.dao;

import java.sql.SQLException;

import com.emporganizer.models.Login;

public interface LoginDao {
	
	public boolean isValidUser(Login login)throws SQLException;


}
