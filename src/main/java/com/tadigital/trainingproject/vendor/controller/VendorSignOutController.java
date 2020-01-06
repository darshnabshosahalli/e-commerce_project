package com.tadigital.trainingproject.vendor.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendorsignout")
public class VendorSignOutController {

	@GetMapping
	public String SignOutVendor(HttpSession session) {
		
		session.invalidate();
		return "EntryPage.jsp"; 
	
	}
	
}
