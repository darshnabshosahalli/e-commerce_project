package com.tadigital.trainingproject.vendor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tadigital.trainingproject.vendor.dao.VendorDao;
import com.tadigital.trainingproject.vendor.entity.vendor;

@Component
public class RegisterVendorServiceImpl implements RegisterVendorService {
	
	VendorDao vendDao;
	
	@Autowired
	public RegisterVendorServiceImpl(VendorDao vendDao) {
		this.vendDao = vendDao;
	}

	@Override
	public boolean registerCustomer(String name, String email, String password) {
		
		String nameArray[] = name.split(" ");
		String fname;
		String lname;
		
		if(nameArray.length > 1) {
			fname = nameArray[0];
			lname = nameArray[1];
		}
		else {
			fname = name;
			lname = "";
		}
		
		vendor vend = new vendor();
		
		vend.setFname(fname);
		vend.setLname(lname);
		vend.setEmail(email);
		
		int rows = this.vendDao.RegisterCustomer(vend,password);
		if(rows > 0) {
			return true;
		} 
		return false;
	}

	@Override
	public vendor LoginVendor(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
