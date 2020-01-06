package com.tadigital.trainingproject.customer.service;

public interface CustomerChangePasswordService {

	boolean changePassword(String oldpassword, String newpass, String renewpass, String email);

}