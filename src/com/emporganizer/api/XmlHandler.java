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
import java.util.List;

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

        for(Employee tmpEmloyee : employees){
            String id = String.valueOf(tmpEmloyee.getId());

            //employee
            xml.append(EMPLOYEE);
            xml.setAttributes(ID,id);

            //basic info
            xml.append(FIRSTNAME);
            xml.appendText(tmpEmloyee.getFirstName());
            xml.parent();
            xml.append(LASTNAME);
            xml.appendText(tmpEmloyee.getLastName());
            xml.parent();
            xml.append(DOB);
            xml.appendText(tmpEmloyee.getDob().toString());
            xml.parent();

            //contact
            xml.append(CONTACT);
            xml.append(PHONE);
            xml.appendText(tmpEmloyee.getPhone());
            xml.parent();
            xml.append(EMAIL);
            xml.appendText(tmpEmloyee.getEmail());
            xml.parent();
            xml.parent();

            //address
            Address address = tmpEmloyee.getAddress();
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
            EmploymentDetail detail = tmpEmloyee.getDetail();
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
}
