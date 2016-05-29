package com.emporganizer.dao.login;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.emporganizer.models.Login;



public class LoginDaoImpl implements LoginDao{
	@Autowired
	 DataSource dataSource;

	@Override
	public boolean isValidUser(Login login) throws SQLException {
		String query = "Select count(1) from login where username = ? and password = ?";
        PreparedStatement pstmt = dataSource.getConnection().prepareStatement(query);
        pstmt.setString(1, login.getUserName());
        pstmt.setString(2, login.getPassword());
        ResultSet resultSet = pstmt.executeQuery();

        if(resultSet.next())
        	return (resultSet.getInt(1) > 0);

        else
           return false;

	}

	

}
