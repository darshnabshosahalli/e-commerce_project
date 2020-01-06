package com.tadigital.trainingproject.vendor.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tadigital.trainingproject.customer.controller.CustomerAccountController;
import com.tadigital.trainingproject.vendor.dao.VendorDao;
import com.tadigital.trainingproject.vendor.service.RegisterVendorService;

@Controller
@RequestMapping("/registerVendor")
public class RegisterController {
	
	Logger logger = LogManager.getLogger(RegisterController.class);
	
	RegisterVendorService regVendor;
	
	@Autowired
	public RegisterController(RegisterVendorService regVendor) {
		this.regVendor = regVendor;
	} 

	@PostMapping
	public String registerVendor(HttpServletRequest request) {
		logger.info("registerVendor started");
		
		String name = (String) request.getParameter("name");
		String email = (String) request.getParameter("email");
		String password = (String) request.getParameter("password");
		
		boolean status = regVendor.registerCustomer(name,email,password);
		
		request.setAttribute("REGISTRATIONSTATUS",status);
		
		logger.info("registerVendor ended");
		
		return "VendorSignInSignUpForms.jsp";
		
	}	
}
