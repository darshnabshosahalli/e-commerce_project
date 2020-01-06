package com.tadigital.trainingproject.vendor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/vendor")
public class SignUpSignInPage {
	
	@GetMapping
	public String getVendorSignUpPage() {
		return "VendorSignInSignUpForms.jsp";
	}
	
}
