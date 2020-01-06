package com.tadigital.trainingproject.customer.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tadigital.trainingproject.customer.entity.Customer;
import com.tadigital.trainingproject.customer.service.CustomerChangePasswordService;

@Controller
@RequestMapping("/changepassword")
public class CustomerChangePassword {
	
	Logger logger = LogManager.getLogger(CustomerChangePassword.class);
	
	CustomerChangePasswordService custChangePassword;
	
	@Autowired
	public CustomerChangePassword(CustomerChangePasswordService custChangePassword) {
		
		logger.info("CustomerChangePasswordService dependency injected");
		this.custChangePassword = custChangePassword;
	}

	@GetMapping
	public String getChangePassword() {
		
		logger.info("returning change password page");
		return "ChangePassword1.jsp";
	}
	
	@PostMapping
	public String ChangePassword(HttpServletRequest request) {
		
		logger.info("ChangePassword execution started");
		String oldpassword = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		String renewpass = request.getParameter("renewpass");
		HttpSession session = request.getSession();
		Customer cust = (Customer) session.getAttribute("CUSTOMEROBJECT");
		String email = cust.getEmail();
		
		boolean status = this.custChangePassword.changePassword(oldpassword,newpass,renewpass,email);
		
		request.setAttribute("CHANGEPASSWORD",status);
		
		logger.info("ChangePassword execution ended");
		
		return "ChangePassword1.jsp";
	}
	
	
}
