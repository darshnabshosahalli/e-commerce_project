package com.tadigital.trainingproject.customer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/whishlist")
public class CustomerWhislistController {

	@GetMapping
	public String getwishlistPage() {
		return "CustomerWishList.jsp";
	}
}
