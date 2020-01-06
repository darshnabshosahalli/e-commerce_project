package com.tadigital.trainingproject.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class CustomerOrdersController {
	
	@GetMapping
	public String getOrdersPage() {
		
		return "CustomerOrders.jsp";
	}
	
}
