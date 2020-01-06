package com.tadigital.trainingproject.customer.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadigital.trainingproject.customer.service.CustomerRegisterService;

@Controller
public class CustomerSignUpController {
	
	Logger logger = LogManager.getLogger(CustomerSignUpController.class);

	CustomerRegisterService custRegister;
	
	@Autowired
	public CustomerSignUpController(CustomerRegisterService custRegister) {
		
		logger.info("CustomerRegisterService dependency injected");
		this.custRegister = custRegister;
	}
	
	@RequestMapping("/register")
	public String registerUser(@RequestParam String name,@RequestParam String email,@RequestParam String password,HttpServletRequest request) {
		
		logger.info("registerUser execution started");
		
		boolean status = this.custRegister.Register(name,email,password);
		if(status) {
			this.custRegister.sendRegistrationMail(name, email);
		}
		request.setAttribute("REGISTRATIONSTATUS",status);
		
		logger.info("registerUser execution ended");
		
		return "SignInSignUpForms.jsp";
		
	}
}
