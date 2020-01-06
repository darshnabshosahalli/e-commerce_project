package com.tadigital.trainingproject.customer.service;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.customer.controller.CustomerSignUpController;
import com.tadigital.trainingproject.customer.dao.CustomerDao;

@Component
public class CustomerChangePasswordServiceImpl implements CustomerChangePasswordService {
	
	Logger logger = LogManager.getLogger(CustomerChangePasswordServiceImpl.class);
	
	CustomerDao custDao;
	
	@Autowired
	public CustomerChangePasswordServiceImpl(CustomerDao custDao) {
		
		logger.info("CustomerDao dependency injected");
		this.custDao = custDao;
	}

	@Override
	public boolean changePassword(String oldpassword, String newpass, String renewpass, String email) {
		
		logger.info("changePassword execution started");
		
		String currPassword = this.custDao.getCurrPassword(email);
		if(currPassword.equals(oldpassword) && newpass.equals(renewpass) && !currPassword.contentEquals(newpass)) {
			int rows = this.custDao.UpdatePassword(email,newpass);
			if (rows > 0) {
				
				rows = this.custDao.UpdateSession(email);
				if(rows > 0) {
					return true;
				}
			}
		}
		
		
		logger.info("changePassword execution ended");
		
		return false;
	}

	
	
}
