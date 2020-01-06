package com.tadigital.trainingproject.customer.service;

public interface CustomerRegisterService {

	boolean Register(String name, String email, String password);

	void sendRegistrationMail(String name, String email);

}