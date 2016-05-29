package com.emporganizer.dao.login;

import java.sql.SQLException;

import com.emporganizer.models.Login;

public interface LoginDao{
	
	public boolean isValidUser(Login login)throws SQLException;


}
