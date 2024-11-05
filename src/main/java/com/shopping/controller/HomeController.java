package com.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shopping.service.KidsDressService;
import com.shopping.service.MensDressService;
import com.shopping.service.WomensDressService;

@Controller
public class HomeController {
	
	@Autowired
	private MensDressService mensDressService;
	
	@Autowired
	private WomensDressService womensDressService;
	
	@Autowired
	private KidsDressService kidsDressService;

	@GetMapping("/")
	public String getForm() {
		return "home";
	}

	@GetMapping("/aboutus")
	public String getAboutUs() {
		return "aboutus";
	}

	@GetMapping("/contactus")
	public String getContactUs() {
		return "contactus";
	}

	@GetMapping("/shop")
	public String shop(Model model) {
		model.addAttribute("mens",mensDressService.getAllProducts());
		model.addAttribute("womens",womensDressService.getAllProducts());
		model.addAttribute("kids",kidsDressService.getAllProducts());
		return "products";
	}
}
