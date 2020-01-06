package com.tadigital.trainingproject.vendor.service;

import com.tadigital.trainingproject.vendor.entity.vendor;

public interface RegisterVendorService {

	boolean registerCustomer(String name, String email, String password);

	public vendor LoginVendor(String name, String password);

}