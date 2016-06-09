<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

							<form:form method="post" action="updateEmployee" modelAttribute="employeeHelper">
							<form:input type="hidden" path="id" value="${employeeHelper.id}" />
							<table>
								<tr>
									<td><h2>Employee</h2></td>							
								</tr>
								
								<tr>
									<td>Surname:</td>
									<td><form:input type="text" path="lastName" name="lastName" required="true"/></td>
								</tr>
								<tr>
									<td>Date of birth:</td>
									<td><form:input type="text" path="country" name="country" required="true"/></td>
								</tr>
								<tr>
									<td>
										<input type="submit" value="save"/>
									</td>
								</tr>
								
							</table>
						</form:form> 

</body>
</html>