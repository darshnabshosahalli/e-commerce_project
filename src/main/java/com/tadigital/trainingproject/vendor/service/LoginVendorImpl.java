package com.tadigital.trainingproject.vendor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.vendor.dao.VendorDao;
import com.tadigital.trainingproject.vendor.entity.vendor;

@Component
public class LoginVendorImpl implements LoginVendor {

	VendorDao vendDao;
	
	@Autowired
	public LoginVendorImpl(VendorDao vendDao) {
		this.vendDao = vendDao;
	}
	
	public vendor LoginVendor(String name, String password) {
		
		vendor vend = this.vendDao.LoginCustomer(name, password);
		return vend;
	}

}
