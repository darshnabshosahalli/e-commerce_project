package com.tadigital.trainingproject.customer.service;

import com.tadigital.trainingproject.customer.entity.Customer;

public interface GetCustomerDataService {

	Customer getCustomerData(String email, String id);

}