package com.tadigital.trainingproject.vendor.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tadigital.trainingproject.vendor.entity.vendor;
import com.tadigital.trainingproject.vendor.service.LoginVendor;
import com.tadigital.trainingproject.vendor.service.RegisterVendorService;

@Controller
@RequestMapping("/vendorlogin")
public class LoginController {
	
	LoginVendor logVendor;
	
	@Autowired
	public LoginController(LoginVendor logVendor) {
		this.logVendor = logVendor;		
	} 

	@GetMapping
	public String getHomePage() {
		
		return "VendorHomePage.jsp";
	}
	
	
	@PostMapping
	public String LoginVendor(HttpServletRequest request) {
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		vendor vend = this.logVendor.LoginVendor(email,password);
		
		
		
		if(vend != null) {
			
			HttpSession session = request.getSession();
			session.setAttribute("VENDOROBJECT", vend);
			request.setAttribute("LOGINPROCESS", true);
			
		}
		else {
			
			request.setAttribute("LOGINPROCESS", false);
			return "VendorSignInSignUpForms.jsp";
			
		}
		
		return "VendorHomePage.jsp";
		
	}
}
