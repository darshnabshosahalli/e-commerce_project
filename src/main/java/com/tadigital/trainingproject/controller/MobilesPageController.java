package com.tadigital.trainingproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/mobiles")
public class MobilesPageController {

	@GetMapping
	public String getMobilesPage() {
		return "Mobiles1.jsp";
	}
}
