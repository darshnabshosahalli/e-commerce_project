package com.tadigital.trainingproject.customer.dao;

import com.tadigital.trainingproject.customer.entity.Address;
import com.tadigital.trainingproject.customer.entity.Customer;

public interface CustomerDao {

	int RegisterUser(String fname, String lname, String email, String password);
	public Customer LoginUser(String email,String password,Customer cust);
	public Customer getCustomerData(String email,String id);
	public void AddSession(String id,String email);
	public String getCurrPassword(String email);
	public int UpdatePassword(String email, String newpass);
	public int UpdateSession(String email);
	public Address getAddress(int custId);
	public int updateAddress(Address address);
	public int updateDetails(Customer customer);
	public int updateImageId(double imageID,int cust_id);
	public String getImageId(int custId);
	

}