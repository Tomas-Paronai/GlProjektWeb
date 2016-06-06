package com.emporganizer.api;

import com.emporganizer.api.XML.XmlIterator;
import com.emporganizer.api.XML.XmlOut;
import com.emporganizer.api.XML.XmlRead;
import com.emporganizer.api.XML.errors.XmlParserException;
import com.emporganizer.models.employee.Address;
import com.emporganizer.models.employee.Employee;
import com.emporganizer.models.employee.EmploymentDetail;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.xml.soap.Node;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Created by tomas on 6/4/2016.
 */
public class XmlHandler {

    private XmlOut xmlOut;
    private XmlRead xmlRead;
    private XmlIterator xml;

    public final static String ROOT = "employees";
    public final static String EMPLOYEE = "employee";
    public final static String ID = "id";
    public final static String FIRSTNAME = "first-name";
    public final static String LASTNAME = "last-name";
    public final static String SEX = "sex";
    public final static String DOB = "dob";
    public final static String CONTACT = "contact";
    public final static String PHONE = "phone";
    public final static String EMAIL = "email";
    public final static String ADDRESS = "address";
    public final static String COUNTRY = "country";
    public final static String CITY = "city";
    public final static String STREET = "street";
    public final static String POSTCODE = "post-code";
    public final static String DETAIL = "detail";
    public final static String POSITION = "position";
    public final static String CONTRACT = "contract";
    public final static String SALARY = "salary";
    public final static String EMPLOYED_SINCE = "employed-since";

    //TODO past shifts xml parsng


    public File exportEmployees(List<Employee> employees) throws XmlParserException, IOException {        
        if(xml == null){
            xml = new XmlIterator();
        }
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "exports");
        if(!dir.exists()){
        	dir.mkdir();
        }
        
        File fileResult = new File(dir.getAbsolutePath()+File.separator+"employees.xml");
        if(!fileResult.exists()){
        	fileResult.createNewFile();
        }
        xml.setFile(fileResult);
        xml.createElement(ROOT);

        for(Employee tmpEmployee : employees){
            String id = String.valueOf(tmpEmployee.getId());

            //employee
            xml.append(EMPLOYEE);
            xml.setAttributes(ID,id);

            //basic info
            xml.append(FIRSTNAME);
            xml.appendText(tmpEmployee.getFirstName());
            xml.parent();
            xml.append(LASTNAME);
            xml.appendText(tmpEmployee.getLastName());
            xml.parent();
            xml.append(SEX);
            xml.appendText(tmpEmployee.getSex().getVal());
            xml.parent();
            xml.append(DOB);
            xml.appendText(tmpEmployee.getDob().toString());
            xml.parent();

            //contact
            xml.append(CONTACT);
            xml.append(PHONE);
            xml.appendText(tmpEmployee.getPhone());
            xml.parent();
            xml.append(EMAIL);
            xml.appendText(tmpEmployee.getEmail());
            xml.parent();
            xml.parent();

            //address
            Address address = tmpEmployee.getAddress();
            xml.append(ADDRESS);
            xml.append(COUNTRY);
            xml.appendText(address.getCountry());
            xml.parent();
            xml.append(CITY);
            xml.appendText(address.getCity());
            xml.parent();
            xml.append(STREET);
            xml.appendText(address.getStreet());
            xml.parent();
            xml.append(POSTCODE);
            xml.appendText(address.getPostCode());
            xml.parent();
            xml.parent();

            //detail
            EmploymentDetail detail = tmpEmployee.getDetail();
            xml.append(DETAIL);
            xml.append(POSITION);
            xml.appendText(detail.getPosition());
            xml.parent();
            xml.append(CONTRACT);
            xml.appendText(detail.getContract());
            xml.parent();
            xml.append(SALARY);
            xml.appendText(String.valueOf(detail.getSalary()));
            xml.parent();
            xml.append(EMPLOYED_SINCE);
            xml.appendText(detail.getWorkSince().toString());
            xml.root();
        }

        if(xml.build()){
            return fileResult;
        }
        return null;
    }
    
    public List<Employee> importEmployees(File importFile){
    	List<Employee> result = new ArrayList<>();
    	
    	String extension = "";

    	int n = importFile.getName().lastIndexOf('.');
    	if (n > 0) {
    	    extension = importFile.getName().substring(n+1);
    	}
    	
    	if(extension.equals("xml")){
    		if(xml == null){
                xml = new XmlIterator();
            }
    		
    		NodeList nodeList = xml.getDoc().getElementsByTagName(EMPLOYEE);
    		for(int i = 0; i < nodeList.getLength(); i++){
    			if(nodeList.item(i).getNodeType() == Node.ELEMENT_NODE){    				
    				xml.setElement((Element)nodeList.item(i));
    				
    				xml.firstChild();
    				String firstName = xml.readValue();
    				xml.nextSibling();
    				String lastName = xml.readValue();
    				xml.nextSibling();
    				String sex = xml.readValue();
    				xml.nextSibling();
    				String dob = xml.readValue();
    				
    				xml.parent();
    				xml.findElement(CONTACT);
    				xml.firstChild();
    				String phone = xml.readValue();
    				xml.nextSibling();
    				String email = xml.readValue();
    				
    				xml.parent();
    				xml.parent();
    				xml.findElement(ADDRESS);
    				xml.firstChild();
    				String country = xml.readValue();
    				xml.nextSibling();
    				String city = xml.readValue();
    				xml.nextSibling();
    				String street = xml.readValue();
    				xml.nextSibling();
    				String postcode = xml.readValue();
    				
    				xml.parent();
    				xml.parent();
    				xml.findElement(DETAIL);
    				xml.firstChild();
    				String position = xml.readValue();
    				xml.nextSibling();
    				String contract = xml.readValue();
    				xml.nextSibling();
    				String salary = xml.readValue();
    				xml.nextSibling();
    				String employedSince = xml.readValue();
    				
    				Address address = new Address(country, city, street, postcode);
    				EmploymentDetail detail = new EmploymentDetail(position, contract, Float.parseFloat(salary), employedSince);
    				Employee tmpEmployee = new Employee(0,firstName,lastName,sex,dob,phone,email,address,detail);
    				result.add(tmpEmployee);
    			}
    		}
    	}
    	else{
    		return null;
    	}
    	
    	return result;
    }
}
