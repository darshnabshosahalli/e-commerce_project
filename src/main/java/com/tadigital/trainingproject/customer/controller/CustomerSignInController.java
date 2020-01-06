package com.tadigital.trainingproject.customer.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.tadigital.trainingproject.customer.entity.Customer;
import com.tadigital.trainingproject.customer.service.CustomerLoginService;

@Controller
public class CustomerSignInController {
	
	Logger logger = LogManager.getLogger(CustomerSignInController.class);

	CustomerLoginService custLogin;
	
	public CustomerSignInController(CustomerLoginService custLogin) {
		
		logger.info("CustomerLoginService dependency injected");
		this.custLogin = custLogin;
	}
	
	@RequestMapping("/login")
	public String LoginCustomer(@RequestParam String email, @RequestParam String password,HttpServletRequest request,HttpServletResponse response) {
		
		logger.info("LoginCustomer execution started");
		
		Customer cust = this.custLogin.loginCustomer(email, password);
		if(cust != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("CUSTOMEROBJECT", cust);
			request.setAttribute("LOGINPROCESS", true);
			Cookie[] cookies = request.getCookies();
			String staysignedin = request.getParameter("staysignedin");
			
			for(Cookie cookieName : cookies) {
				if(cookieName.getName().equals("ECOMMERCE")) {
					
				}
				else {
					if(staysignedin != null) {
						
						Cookie cookie = new Cookie("ECOMMERCE",email+"-"+session.getId());
						cookie.setMaxAge(60*24*30);
						this.custLogin.addSessionToDB(session.getId(),email);
						response.addCookie(cookie);
						
						break;
					}
				}
			}
			
			logger.info("LoginCustomer execution ended");
			
			return "EntryPage.jsp";
		}
		else {
			request.setAttribute("LOGINPROCESS", false);
		}
		
		logger.info("LoginCustomer execution ended");
		
		return "SignInSignUpForms.jsp";
	}
}
