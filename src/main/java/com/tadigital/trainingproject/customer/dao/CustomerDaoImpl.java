package com.tadigital.trainingproject.customer.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.customer.controller.CustomerSignUpController;
import com.tadigital.trainingproject.customer.entity.Address;
import com.tadigital.trainingproject.customer.entity.Customer;
import com.tadigital.trainingproject.dao.Dao;

@Component
public class CustomerDaoImpl extends Dao implements CustomerSql, CustomerDao{
	
	Connection con = null;
	PreparedStatement stmt = null;
	
	Logger logger = LogManager.getLogger(CustomerDaoImpl.class);
	
	public CustomerDaoImpl() {
		
	}

	@Override
	public int RegisterUser(String fname, String lname,String email, String password) {
		
		logger.info("RegisterUser started");
		
		con = getConnection();
		stmt = createStatement(con, SQL_REGISTER_CUSTOMER);
		int rows = 0;
		try {
			stmt.setString(1,fname);
			stmt.setString(2,lname);
			stmt.setString(3,email);
			stmt.setString(4,password);
			
			rows = stmt.executeUpdate();
		} catch (SQLException e) {
			logger.error(e);
		}
		closeStatement();
		
		logger.info("RegisterUser ended");
		
		return rows;
	}
	
	public Customer LoginUser(String email,String password,Customer cust) {
		
		logger.info("LoginUser started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_LOGIN_USER);
		try {
			stmt.setString(1,email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				cust = new Customer();
				cust.setCustId(rs.getInt(1));
				cust.setfName(rs.getString(2));
				cust.setlName(rs.getString(3));
				cust.setGender(rs.getString(4));
				cust.setDob(rs.getString(5));
				cust.setEmail(email);
				cust.setContact(rs.getString(8));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("LoginUser ended");
			
		return cust;
		
	}
	
	public Customer getCustomerData(String email,String id) {
		
		logger.info("getCustomerData started");
				
		con = getConnection();
		stmt = createStatement(con,SQL_GET_CUSTOMER);
		Customer cust = null;
		try {
			stmt.setString(1,email);
			stmt.setString(2,id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				cust = new Customer();
				cust.setCustId(rs.getInt(1));
				cust.setfName(rs.getString(2));
				cust.setlName(rs.getString(3));
				cust.setGender(rs.getString(4));
				cust.setGender(rs.getString(5));
				cust.setEmail(email);
				cust.setContact(rs.getString(8));
			}
		} catch (SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("getCustomerData ended");
			
		return cust;		
	}
	
	public void AddSession(String id,String email) {
		
		logger.info("AddSession started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_ADD_SSID);
		try {
			stmt.setString(1,id);
			stmt.setString(2,email);
			stmt.executeUpdate();
			
		} catch (SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("AddSession ended");
			
				
	}
	
	public String getCurrPassword(String email) {
		
		logger.info("getCurrPassword Started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_CURR_PASS);
		String currPassword = null;
		try {
			
			stmt.setString(1,email);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				currPassword = rs.getString("cust_pwd");
			}
		} catch(SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("getCurrPassword ended");
		
		return currPassword;
	}
	
	public int UpdatePassword(String email, String newpass) {
		
		logger.info("UpdatePassword Started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_UPDATE_PASS);
		int rows = 0;
		
		try {
			
			stmt.setString(1,newpass);
			stmt.setString(2,email);
			rows = stmt.executeUpdate();
			
		} catch(SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("UpdatePassword ended");
		
		return rows;
		
	}
	
	public int UpdateSession(String email) {
		
		logger.info("UpdateSession started");
	
		con = getConnection();
		stmt = createStatement(con,SQL_UPDATE_SESS);
		int rows = 0;
		
		try {
			
			stmt.setString(1,"NULL");
			stmt.setString(2,email);
			rows = stmt.executeUpdate();
		} catch(SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("UpdateSession ended");
		
		return rows;
		
	}
	
	public Address getAddress(int custId) {
		
		logger.info("getAddress started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_GET_ADDRESS);
		Address address = new Address();
		try {
			stmt.setInt(1, custId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				address.setAdd_id(rs.getInt(1));
				address.setAddress(rs.getString(3));
				address.setCity(rs.getString(4));
				address.setCountry(rs.getString(7));
				address.setPincode(rs.getString(5));
				address.setState(rs.getString(6));
			}
		} catch(SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("getAddress ended");
		
		return address;
	}
	
	public int updateAddress(Address address) {
		
		logger.info("updateAddress started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_UPDATE_ADDRESS);
		int rows = 0;
		
		try {
			stmt.setString(1, address.getAddress());
			stmt.setString(2,address.getCity());
			stmt.setString(3, address.getPincode());
			stmt.setString(4, address.getState());
			stmt.setString(5, address.getCountry());
			stmt.setInt(6, address.getCust_id());
			
			rows = stmt.executeUpdate();
			
		} catch(SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("updateAddress ended");
		
		return rows;
		
		
	}

	@Override
	public int updateDetails(Customer customer) {
		
		logger.info("updateDetails started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_UPDATE_DETAILS);
		int rows = 0;
		
		try {
			stmt.setString(1,customer.getfName());
			stmt.setString(2,customer.getlName());
			stmt.setString(3,customer.getGender() );
			stmt.setString(4,customer.getDob());
			stmt.setString(5,customer.getEmail());
			stmt.setString(6,customer.getContact());
			stmt.setInt(7,customer.getCustId());
			
			rows = stmt.executeUpdate();
			
		} catch(SQLException e) {
			logger.error(e);
		}
		
		closeStatement();
		
		logger.info("updateDetails ended");
		
		return rows;
		
	}
	
	@Override
	public int updateImageId(double imageID,int cust_id) {
		
		logger.info("updateImageId started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_UPDATE_IMAGEID);
		int rows = 0;
		
		try {
			stmt.setString(1,imageID+"");
			stmt.setInt(2,cust_id);
			rows = stmt.executeUpdate();
			
		} catch(SQLException e) {
			logger.error(e);
		}
		
		logger.info("updateImageId ended");
		
		return rows;
		
	}

	@Override
	public String getImageId(int custId) {
		
		logger.info("getImageId started");
		
		con = getConnection();
		stmt = createStatement(con,SQL_GET_IMAGEID);
		ResultSet rs;
		String imageID = "";
		try {
			stmt.setInt(1, custId);
			rs = stmt.executeQuery();
			while(rs.next()) {
				imageID = rs.getString("imageid");
			}
		} catch(SQLException e) {
			logger.error(e);
		}
		
		logger.info("getImageId ended");
		
		return imageID;
	}
}
	
