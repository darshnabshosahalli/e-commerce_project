package com.tadigital.trainingproject.vendor.dao;

public interface VendorSql {

	String SQL_REGISTER_VENDOR = "INSERT INTO vendor_personal_details(fname,lname,email,password) VALUES(?,?,?,?)";
	String SQL_CONFIRM_USER = "SELECT * FROM vendor_personal_details WHERE email=? AND password=?";

}
