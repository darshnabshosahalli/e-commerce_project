package com.tadigital.trainingproject.customer.service;

import com.tadigital.trainingproject.customer.entity.Address;
import com.tadigital.trainingproject.customer.entity.Customer;

public interface CustomerGetAddress {

	Address getCustomerAddress(int custId);

	boolean updateCustomerAddress(Address address);

	public boolean updateCustomerDetails(Customer customer);

	boolean getImageStatus(String email);

	long getImagelastModified(String email);

	public String getImageID(int custId);
	
}