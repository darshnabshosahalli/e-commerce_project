package com.tadigital.trainingproject.vendor.dao;

import com.tadigital.trainingproject.vendor.entity.vendor;

public interface VendorDao {

	public int RegisterCustomer(vendor vend, String password);

	public vendor LoginCustomer(String name, String password);

}