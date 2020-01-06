package com.tadigital.trainingproject.controller;



import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tadigital.trainingproject.service.SendContactEmail;

@Controller
@RequestMapping("/contact")
public class ContactpageController {
	
	Logger logger = LogManager.getLogger(ContactpageController.class);
	
	SendContactEmail custMail;
	
	@Autowired
	public void setCustMail(SendContactEmail custMail) {
		logger.info("SendContactEmail dependecy injected");
		this.custMail = custMail;
	}

	@GetMapping
	public String getContactPage() {
		return "Contact1.jsp";
	}
	
	@PostMapping
	public String contactCustomer(HttpServletRequest request) {
		
		logger.info("contactCustomer execution started");
	
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String msg = request.getParameter("msg");
		
		boolean status = custMail.sendEmailToCust(fname,lname,email,subject,msg);
		request.setAttribute("CONTACTSTATUS",status);
		
		logger.info("contactCustomer execution ended");
		
		return "Contact1.jsp";
	}
}
