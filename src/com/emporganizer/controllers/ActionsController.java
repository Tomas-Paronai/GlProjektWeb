package com.emporganizer.controllers;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.emporganizer.api.EmpMail;
import com.emporganizer.api.beans.SelectedEmp;
import com.emporganizer.dao.employee.EmployeeDAO;
import com.emporganizer.dao.items.ItemDAO;
import com.emporganizer.models.Login;
import com.emporganizer.models.MailFormBean;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.items.DBItem;
import com.emporganizer.models.items.Item;

@Controller
public class ActionsController {
		
	@Autowired
	EmpMail empMail;
	
	@Autowired
	EmployeeDAO employeeDAO;
	
	@Autowired
	ItemDAO itemDAO;
	
	@RequestMapping(value = "checkboxList", method = RequestMethod.GET)
	public String getCheckBoxList(@RequestParam(value = "for", required = true) String nextPage, ModelMap model){
		model.addAttribute("employees", employeeDAO.getEmployeeList());
		model.addAttribute("action", nextPage);
		model.addAttribute("hint", getHint(nextPage));
		model.addAttribute("selectedEmp",new SelectedEmp());
		return "/comp/employeeListCheck";
	}


	@RequestMapping(value = "/mailForm", method = RequestMethod.POST)
	public String getMailForm(@ModelAttribute("selectedEmp") SelectedEmp selectedEmp, ModelMap model){
		model.addAttribute("mailFormBean", new MailFormBean());
		
		List<Employee> employees = employeeDAO.getEmployeeByListId(selectedEmp.getEmpIds());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < employees.size(); i++){
			sb.append(employees.get(i).getEmail());
			if(i + 1 < employees.size()){
				sb.append(";");
			}
		}
		
		model.addAttribute("recipents", sb.toString());
		return "pages/dialog/sendMail";
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(@ModelAttribute("mailFormBean") MailFormBean mailFormBean){
		
		return "redirect: /pages/home";
	}
	
	@RequestMapping(value = "newData", method = RequestMethod.GET)
	public String getDialogForm(@RequestParam(value = "item", required = true) String name, ModelMap model){
		model.addAttribute("table", name);
		//model.addObject("formAction","newData?item="+name);
		model.addAttribute("dialogTitle","manage "+name+"s");
		model.addAttribute("items", itemDAO.getItems(name));
		return "pages/dialog/newItem";
	}
	
	@RequestMapping(value = "newData", method = RequestMethod.POST)
	public String processData(@ModelAttribute("item") Item item, @RequestParam(value = "item", required = true) String name){
		
		return "redirect: /pages/home";
	}
	
	@RequestMapping(value = "/saveItem", method = RequestMethod.POST)
	public @ResponseBody void saveItem(@RequestParam(value = "i_item", required = true) int id,
									   @RequestParam(value = "d_item", required = true) String name,
									   @RequestParam(value = "table", required = true) String table){
		itemDAO.updateItem(id, name, table);
	}
	
	@RequestMapping(value = "/insertItem", method = RequestMethod.POST)
	public @ResponseBody DBItem saveItem(@RequestParam(value = "d_item", required = true) String name,
			 							 @RequestParam(value = "table", required = true) String table){		
		itemDAO.insertItem(name, table);
		return itemDAO.getLastItem(table);
	}
	
	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST)
	public @ResponseBody void saveItem(@RequestParam(value = "i_item", required = true) int id,
			 						     @RequestParam(value = "table", required = true) String table){		
		itemDAO.deleteItem(id, table);
	}

	
	private String getHint(String nextPage) {
		if(nextPage.equals("mailForm")){
			return "Choose employees to send email to.";
		}
		return null;
	}
	
}
