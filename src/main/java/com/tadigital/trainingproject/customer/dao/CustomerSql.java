package com.tadigital.trainingproject.customer.dao;

public interface CustomerSql {

	String SQL_REGISTER_CUSTOMER = "INSERT INTO customer_personal_information(cust_fname,cust_lname,cust_email,cust_pwd) VALUES (?,?,?,?)";
	String SQL_LOGIN_USER = "SELECT * FROM customer_personal_information WHERE cust_email= ? AND cust_pwd= ?";
	String SQL_GET_CUSTOMER = "SELECT * FROM customer_personal_information WHERE cust_email= ? AND sessionid= ?";
	String SQL_ADD_SSID = "UPDATE customer_personal_information SET sessionid= ? WHERE cust_email= ?";
	String SQL_CURR_PASS = "SELECT cust_pwd FROM customer_personal_information WHERE cust_email= ?";
	String SQL_UPDATE_PASS = "UPDATE customer_personal_information SET cust_pwd= ? WHERE cust_email= ?";
	String SQL_UPDATE_SESS = "UPDATE customer_personal_information SET sessionid= ? WHERE cust_email= ?";
	String SQL_GET_ADDRESS = "SELECT * FROM customer_address_information WHERE cust_id= ?";
	String SQL_UPDATE_ADDRESS = "UPDATE customer_address_information SET cust_address=?,cust_city=?,cust_pincode=?,cust_state=?,cust_country=? WHERE cust_id=?";
	String SQL_UPDATE_DETAILS = "UPDATE customer_personal_information SET cust_fname=?,cust_lname=?,cust_gender=?,cust_dob=?,cust_email=?,cust_contact=? WHERE cust_id=?";
	String SQL_UPDATE_IMAGEID = "UPDATE customer_personal_information SET imageid=? WHERE cust_id=?";
	String SQL_GET_IMAGEID = "SELECT imageid from customer_personal_information WHERE cust_id=?";

}
