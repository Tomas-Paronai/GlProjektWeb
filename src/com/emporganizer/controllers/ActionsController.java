package com.emporganizer.controllers;

import java.io.*;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.emporganizer.api.XmlHandler;
import com.emporganizer.api.XML.errors.XmlParserException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
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
	
	
	@RequestMapping("getEmployees")
	public String getEmployees(ModelMap model){
		model.addAttribute("employeesCheck", employeeDAO.getEmployeeList());
		return "comp/employeeListCheck";
	}

	@RequestMapping(value = "/mailForm", method = RequestMethod.GET)
	public String getMailForm(ModelMap model){
		model.addAttribute("mailFormBean", new MailFormBean());		
		return "pages/dialog/sendMail";
	}
	
	@RequestMapping(value = "/sendMail", method = RequestMethod.POST)
	public String sendMail(@ModelAttribute("mailFormBean") MailFormBean mailFormBean){
		String recipents[] = mailFormBean.getRecipents().split(";");	
		
		//TODO toast message about succession & fail
		try {
			System.out.println("Sending mails....");
			empMail.newMessage(mailFormBean.getSubject(), mailFormBean.getMessage(), recipents);
			System.out.println("Success!");
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("Failed!");
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "newData", method = RequestMethod.GET)
	public String getDialogForm(@RequestParam(value = "item", required = true) String name, ModelMap model){
		model.addAttribute("table", name);
		//model.addObject("formAction","newData?item="+name);
		model.addAttribute("dialogTitle","manage "+name+"s");
		model.addAttribute("items", itemDAO.getItems(name));
		return "pages/dialog/newItem";
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

	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public String getExportPage(ModelMap model){
		model.addAttribute("employeesCheck", employeeDAO.getEmployeeList());
		model.addAttribute("selectBean",new SelectedEmp());
		return "pages/dialog/export";
	}
	
	@RequestMapping(value = "/export", method = RequestMethod.POST)
	public String exportList(@ModelAttribute("selectBean") SelectedEmp selectedEmp,HttpServletRequest request,HttpServletResponse response){
		XmlHandler xmlHandler = new XmlHandler();
		try {
	        ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        File dir = new File(appPath + File.separator + "imports");
	        if(!dir.exists()){
	        	dir.mkdir();
	        }
			File filetoDownlaod = xmlHandler.exportEmployees(employeeDAO.getEmployeeByListId(selectedEmp.getEmpId()),dir);
			
			if(!filetoDownlaod.exists()){
	            String errorMessage = "Sorry. The file you are looking for does not exist";
	            System.out.println(errorMessage);
	            OutputStream outputStream = response.getOutputStream();
	            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
	            outputStream.close();
	            return "redirect:/home";
	        }
			
			String mimeType= URLConnection.guessContentTypeFromName(filetoDownlaod.getName());
	        if(mimeType==null){
	            System.out.println("mimetype is not detectable, will take default");
	            mimeType = "application/octet-stream";
	        }	         
	        System.out.println("mimetype : "+mimeType);
	         
	        response.setContentType(mimeType);
	        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + filetoDownlaod.getName() +"\""));
	        response.setContentLength((int)filetoDownlaod.length());	        
	        InputStream inputStream = new BufferedInputStream(new FileInputStream(filetoDownlaod));	 
	        FileCopyUtils.copy(inputStream, response.getOutputStream());
	        
		} catch (XmlParserException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "redirect:/home";
	}
	
	@RequestMapping(value = "/import", method = RequestMethod.GET)
	public String importForm(){
		return "pages/dialog/import";
	}
	
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public void importFile(@RequestParam("fileUpload") CommonsMultipartFile fileUpload, HttpServletRequest request){
		if(fileUpload != null){
			XmlHandler xmlHandler = new XmlHandler();
			ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	        File dir = new File(appPath + File.separator + "imports");
	        if(!dir.exists()){
	        	dir.mkdir();
	        }
			File importedfile = new File(dir.getAbsolutePath()+File.separator+fileUpload.getOriginalFilename());
			
			try {
				if(!importedfile.exists()){
					importedfile.createNewFile();
				}
				System.out.println("Importing file...");
				fileUpload.transferTo(importedfile);
				List<Employee> parsedEmployees = xmlHandler.importEmployees(importedfile);
				employeeDAO.insertEmployee(parsedEmployees);
				System.out.println("Success!");
			} catch (IllegalStateException | IOException e) {
				System.out.println("Fail!");
				e.printStackTrace();
			}			
		}		
	}
}
