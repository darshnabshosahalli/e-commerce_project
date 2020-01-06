package com.tadigital.trainingproject.customer.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.customer.dao.CustomerDao;
import com.tadigital.trainingproject.customer.entity.Customer;

@Component
public class CustomerLoginServiceImpl implements CustomerLoginService {
	
	Logger logger = LogManager.getLogger(CustomerLoginServiceImpl.class);

	CustomerDao custDao;
	
	@Autowired
	public CustomerLoginServiceImpl(CustomerDao custDao) {
		
		logger.info("CustomerDao dependency injected");
		this.custDao = custDao;
		
	}
	
	@Override
	public Customer loginCustomer(String email,String password) {
		
		logger.info("loginCustomer execution started");
		
		Customer cust = null;
		
		cust = this.custDao.LoginUser(email,password,cust);
		
		logger.info("loginCustomer execution ended");
		
		return cust;
		
	}

	@Override
	public void  addSessionToDB(String id,String email) {
		
		logger.info("addSessionToDB execution started");
		
		this.custDao.AddSession(id,email);
		
		logger.info("addSessionToDB execution ended");
		
	}
}
