package com.tadigital.trainingproject.controller;



import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tadigital.trainingproject.customer.entity.Customer;
import com.tadigital.trainingproject.customer.service.GetCustomerDataService;

@Controller
public class HompageController {
	
	Logger logger = LogManager.getLogger(HompageController.class);

	GetCustomerDataService custData;
	
	@Autowired
	public HompageController(GetCustomerDataService custData) {
		
		logger.info("GetCustomerDataService injection done");
		this.custData = custData;
	}
	
	@RequestMapping(value= {"/"})
	public String getHomePage(HttpServletRequest request) {
		
		logger.info("getHomePage running");
		
		Customer cust = null;
		Cookie[] cookie = request.getCookies();
		if(cookie != null) {
			for(Cookie cookieName : cookie) {
				if(cookieName.getName().equals("ECOMMERCE")) {
					String value = cookieName.getValue();
					String email = value.split("-")[0];
					String id = value.split("-")[1];
					cust = this.custData.getCustomerData(email,id);
					break;
				}
			}
			HttpSession session = request.getSession();
			session.setAttribute("CUSTOMEROBJECT", cust);
			request.setAttribute("LOGINPROCESS", true);
		}
		
		logger.info("getHomePage ended");
		return "EntryPage.jsp";
		
	}
	
	@RequestMapping("/home")
	public String getHomePage() {
		return "EntryPage.jsp";
	}
}
