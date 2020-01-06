package com.tadigital.trainingproject.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tadigital.trainingproject.customer.service.CustomerRegisterServiceImpl;

public class Dao {
	
	private static Connection con = null;
	private static PreparedStatement stmt = null;
	private static BasicDataSource basicDataSource = null;

	public Dao() {

	}
	
	protected static Connection getConnection() {
		
		if(con == null) {
			synchronized(Dao.class) {
				if(con == null) {
					Properties prop = new Properties();
					InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/tadigital/properties/db.properties");
					try {
						prop.load(input);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					String driverName = prop.getProperty("db.driverclass");
					String connectionUrl = prop.getProperty("db.connectionurl");
					String userName = prop.getProperty("db.username");
					String password = prop.getProperty("db.password");
					basicDataSource = new BasicDataSource();
			        
			        basicDataSource.setDriverClassName(driverName);
			        basicDataSource.setUrl(connectionUrl);
			        basicDataSource.setUsername(userName);
			        basicDataSource.setPassword(password);
			        basicDataSource.setPoolPreparedStatements(true);
			        basicDataSource.setMaxTotal(4);
					
					try {
						Class.forName(driverName);
						con = basicDataSource.getConnection();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		}
		
		return con;
	}
	
	protected static PreparedStatement createStatement(Connection con,String sql) {
		try {
			stmt = con.prepareStatement(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return stmt;
	}
	
	protected void closeStatement() {
		
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	protected void closeConnection() {
		
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
}
