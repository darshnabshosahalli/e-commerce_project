package com.tadigital.trainingproject.vendor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.dao.Dao;
import com.tadigital.trainingproject.vendor.entity.vendor;

@Component
public class VendorDaoImpl extends Dao implements VendorDao,VendorSql {

	Logger logger = LogManager.getLogger(VendorDaoImpl.class);
	
	Connection con;
	PreparedStatement stmt;
	
	@Override
	public int RegisterCustomer(vendor vend, String password) {
		
		con = getConnection();
		stmt = createStatement(con,SQL_REGISTER_VENDOR);
		int rows = 0;
		
		try {
			stmt.setString(1,vend.getFname());
			stmt.setString(2,vend.getLname());
			stmt.setString(3,vend.getEmail());
			stmt.setString(4,password);
			rows = stmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e);
		}
		
		
		return rows;
	}

	@Override
	public vendor LoginCustomer(String email, String password) {
		
		con = getConnection();
		stmt = createStatement(con,SQL_CONFIRM_USER);
		vendor vend = null;
		ResultSet rs = null;
		
		try {
			
			stmt.setString(1,email);
			stmt.setString(2,password);
			rs = stmt.executeQuery();
			while(rs.next()) {
				vend = new vendor();
				vend.setVendor_id(rs.getInt(1));
				vend.setFname(rs.getString(2));
				vend.setLname(rs.getString(3));
				vend.setEmail(rs.getString(4));
			}
			
		} catch (SQLException e) {
			logger.error(e);
		}
		
		return vend;
	}

}
