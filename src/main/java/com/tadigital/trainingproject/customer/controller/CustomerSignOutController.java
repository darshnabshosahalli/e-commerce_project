package com.tadigital.trainingproject.customer.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomerSignOutController {
	
	Logger logger = LogManager.getLogger(CustomerSignOutController.class);
	
	@RequestMapping("signout")
	public String signOut(HttpServletRequest request,HttpServletResponse response) {
		
		logger.info("signOut execution started");
		
		HttpSession session = request.getSession();
		session.removeAttribute("CUSTOMEROBJECT");
		session.invalidate();
		Cookie cookie = new Cookie("ECOMMERCE","");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		logger.info("signOut execution ended");
		
		return "EntryPage.jsp";
		
	}
}
