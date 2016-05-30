package com.emporganizer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.emporganizer.api.EmpMail;
import com.emporganizer.dao.items.ItemDAO;
import com.emporganizer.models.MailFormBean;
import com.emporganizer.models.items.Item;

@Controller
public class ActionsController {
	
	@Autowired
	EmpMail empMail;
	
	@Autowired
	ItemDAO itemDAO;
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.GET)
	public ModelAndView getMailForm(){
		ModelAndView model = new ModelAndView("sendMail");
		model.addObject("mailFormBean", new MailFormBean());
		return model;
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(@ModelAttribute("mailFormBean") MailFormBean mailFormBean){
		
		return "redirect: /pages/home";
	}
	
	@RequestMapping(value = "newData", method = RequestMethod.GET)
	public ModelAndView getDialogForm(@RequestParam(value = "item", required = true) String name){
		ModelAndView model = new ModelAndView(name);
		model.addObject("table", name);
		//model.addObject("formAction","newData?item="+name);
		model.addObject("dialogTitle","manage "+name+"s");
		model.addObject("items", itemDAO.getItems(name));
		return model;
	}
	
	@RequestMapping(value = "newData", method = RequestMethod.POST)
	public String processData(@ModelAttribute("item") Item item, @RequestParam(value = "item", required = true) String name){
		
		return "redirect: /pages/home";
	}
}
