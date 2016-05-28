package com.emporganizer.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@RequestMapping("/home")
	public ModelAndView getHomePage(){
		ModelAndView model = new ModelAndView("pages/home");
		return model;
	}
}
