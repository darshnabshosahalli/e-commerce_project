package com.tadigital.trainingproject.customer.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.tadigital.trainingproject.controller.HompageController;
import com.tadigital.trainingproject.customer.entity.Address;
import com.tadigital.trainingproject.customer.entity.Customer;
import com.tadigital.trainingproject.customer.service.CustomerGetAddress;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

@Controller
public class CustomerAccountController {
	
	Logger logger = LogManager.getLogger(CustomerAccountController.class);

	CustomerGetAddress custAddress;
	
	@Autowired
	public CustomerAccountController(CustomerGetAddress custAddress) {
		logger.info("CustomerGetAddress dependency injected");
		this.custAddress = custAddress;
	}
	
	@GetMapping("/changepersonaldetails")
	public String getCustomerAccount(HttpServletRequest request) {
		
		logger.info("getCustomerAccount execution started");
		
		HttpSession session = request.getSession();
		Customer cust = (Customer) session.getAttribute("CUSTOMEROBJECT");
		Address address = custAddress.getCustomerAddress(cust.getCustId());
		boolean imageStatus = custAddress.getImageStatus(cust.getEmail());
		request.setAttribute("IMAGESTATUS",imageStatus);
		request.setAttribute("ADDRESSOBJECT",address);
		
		logger.info("getCustomerAccount execution ended");
		
		return "CustomerAccount1.jsp";
		
		
	}
	
	@PostMapping("/update")
	public String UpdateCustomerAccount(@RequestParam CommonsMultipartFile file,HttpServletRequest request,HttpSession session) {
		
		logger.info("UpdateCustomerAccount execution started");
		
		//String path = session.getServletContext().getRealPath("/");  
		String path = "D:/Trainee Engineers July 2019/workspace/intermediate/src/main/webapp/images/custimages";
               
        Customer customer = (Customer) session.getAttribute("CUSTOMEROBJECT");
        Address address = custAddress.getCustomerAddress(customer.getCustId());
                
		try{  
	        byte barr[]=file.getBytes();  
	        BufferedOutputStream bout=new BufferedOutputStream(  
	                 new FileOutputStream(path+"/"+customer.getEmail()+".png"));  
	        bout.write(barr);  
	        bout.flush();  
	        bout.close();  
          
        }catch(Exception e){
        	logger.error(e);
        } 
		
		
			
		String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String gender = request.getParameter("gender");
        String dob = request.getParameter("dob");
        String contactNo = request.getParameter("contact");
        String addr = request.getParameter("address");
        String city = request.getParameter("city");
        String pincode = request.getParameter("zip");
        String state = request.getParameter("state");
        String country = request.getParameter("country");
        String email = request.getParameter("email");
        
        customer.setfName(fname);
        customer.setlName(lname);
        customer.setGender(gender);
        customer.setContact(contactNo);
        customer.setDob(dob);
        customer.setEmail(email);
        
        
        
        address.setAddress(addr);
        address.setCity(city);
        address.setCountry(country);
        address.setPincode(pincode);
        address.setState(state);
        address.setCust_id(customer.getCustId());
        
        boolean status = this.custAddress.updateCustomerAddress(address);
        boolean status1 = this.custAddress.updateCustomerDetails(customer);
        if(status == true && status1 == true) {
			request.setAttribute("UPDATEPROCESS",true);
		}
		else {
			request.setAttribute("UPDATEPROCESS",false);
		}
		boolean imageStatus = custAddress.getImageStatus(customer.getEmail());
		request.setAttribute("IMAGESTATUS",imageStatus);
		request.setAttribute("ADDRESSOBJECT", address);
		session.setAttribute("CUSTOMEROBJECT", customer);
		
		logger.info("UpdateCustomerAccount execution ended");
			
		return "CustomerAccount1.jsp";
		
	}
}
