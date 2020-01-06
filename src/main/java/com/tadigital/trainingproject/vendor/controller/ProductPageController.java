package com.tadigital.trainingproject.vendor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductPageController {

	@RequestMapping("/product")
	public String getProductPage() {
		
		return "NewProductForm.jsp";
	}
	
}
