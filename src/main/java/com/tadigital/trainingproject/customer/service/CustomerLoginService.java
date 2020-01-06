package com.tadigital.trainingproject.customer.service;

import com.tadigital.trainingproject.customer.entity.Customer;

public interface CustomerLoginService {

	Customer loginCustomer(String email, String password);

	void addSessionToDB(String id, String email);

}