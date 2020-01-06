package com.tadigital.trainingproject.customer.service;

import java.io.File;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.customer.dao.CustomerDao;
import com.tadigital.trainingproject.customer.entity.Address;
import com.tadigital.trainingproject.customer.entity.Customer;

@Component
public class CustomerGetAddressImpl implements CustomerGetAddress {

	CustomerDao custDao; 
	
	Logger logger = LogManager.getLogger(CustomerGetAddressImpl.class);
	
	@Autowired
	public CustomerGetAddressImpl(CustomerDao custDao) {
		
		logger.info("CustomerDao dependency injected");
		this.custDao = custDao;
	}
	
	@Override
	public Address getCustomerAddress(int custId) {
		
		logger.info("getCustomerAddress execution started");
		
		Address address;
		address = this.custDao.getAddress(custId);
		
		logger.info("getCustomerAddress execution ended");
		
		return address;
	}
	
	@Override
	public boolean updateCustomerAddress(Address address) {
		
		logger.info("updateCustomerAddress execution started");
		
		int rows = this.custDao.updateAddress(address);
		
		logger.info("updateCustomerAddress execution ended");
		
		if(rows > 0) {
			return true;
		}
		
		
		
		return false;
	}

	@Override
	public boolean updateCustomerDetails(Customer customer) {
		
		logger.info("updateCustomerDetails execution started");
		
		int rows = this.custDao.updateDetails(customer);
		
		logger.info("updateCustomerDetails execution ended");
		
		if(rows > 0) {
			
			return true;
		}
		
		
		
		return false;
	}

	@Override
	public boolean getImageStatus(String email) {
		
		logger.info("getImageStatus execution started");
		
		File file = new File("D:/Trainee Engineers July 2019/workspace/intermediate/src/main/webapp/images/custimages"+"/"+email+".png");
		
		logger.info("getImageStatus execution ended");
		
		return file.canRead();
		
	}

	@Override
	public long getImagelastModified(String email) {
		
		logger.info("getImagelastModified execution started");
		
		File file = new File("D:/Trainee Engineers July 2019/workspace/intermediate/src/main/webapp/images/custimages"+"/"+email+".png");
		
		logger.info("getImagelastModified execution ended");
		
		if(file.canRead()) {
			return file.lastModified();
		}
		
		return 0;
	
	}

	@Override
	public String getImageID(int custId) {
		
		return this.custDao.getImageId(custId);
	}
}
