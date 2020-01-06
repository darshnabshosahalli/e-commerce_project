package com.tadigital.trainingproject.customer.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.customer.dao.CustomerDao;
import com.tadigital.trainingproject.customer.entity.Customer;

@Component
public class GetCustomerDataServiceImpl implements GetCustomerDataService {
	
	Logger logger = LogManager.getLogger(GetCustomerDataServiceImpl.class);

	CustomerDao custDao;
	
	@Autowired
	public GetCustomerDataServiceImpl(CustomerDao custDao) {
		
		logger.info("CustomerDao dependecy injected");
		this.custDao = custDao;
	}
	@Override
	public Customer getCustomerData(String email, String id) {
		
		logger.info("getCustomerData started");
		logger.info("getCustomerData ended");
		
		return this.custDao.getCustomerData(email,id);
	}

	
}
