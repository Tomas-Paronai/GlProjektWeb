<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update page </title>
</head>
<body>

		<form:form method="post" action="updateEmployee" modelAttribute="employeeHelper" >
			<form:input type="hidden" path="id" value="${employeeHelper.id}" />
			<!-- main table -->
			<table>
				<!-- First row employee & address -->
				<tr>
				<!--employee cell  -->
					<td>
						<!--  table employee-->
						
							
							<table>
								<tr>
									<td><h2>Employee</h2></td>							
								</tr>
								<tr>
									<td>Name:</td>
									<td><form:input type="text" path="firstName" name="firstName" required="true"/></td>
								</tr>
								<tr>
									<td>Surname:</td>
									<td><form:input type="text" path="lastName" name="lastName" required="true"/></td>
								</tr>
								<tr>
									<td>Date of birth:</td>
									<td><form:input type="text" path="dob" name="dob" required="true"/></td>
								</tr>
								
								
							</table>
					
					</td>
					
					<td>
					<!-- empty cell -->
					</td>	
					
					<td>
					<!--  table salary-->
						
							<table>
								<tr>
									<td><h2>Employment Detail</h2></td>							
								</tr>
								<tr>	
									<td>Contract:</td>
									<td>
										<form:input type="text" path="contract" name="contract" required="true"/>
									</td>
								
								</tr>
								
								<tr>	
									<td>Position:</td>
									<td>
										<form:input type="text" path="position" name="position" required="true"/>
									</td>
								
								</tr>
								<tr>	
									<td>Salary:</td>
									<td>
										<form:input type="text" path="salary" name="salary" required="true"/>
									</td>
								
								</tr>
								<tr>	
									<td>WorkSince:</td>
									<td>
										<form:input type="text" path="workSince" name="workSince" required="true"/>
									</td>
								
								</tr>
								
							</table>
					</td>
				</tr>
				
				<!-- second row salary & contact -->
				<tr>
					<!--address cell  -->
					<td>
					<!--  table address-->
						
							<table>
								<tr>
									<td><h2>Adrress</h2></td>							
								</tr>
								<tr>
									<td>country:</td>
									<td><form:input type="text" path="country" name="country" required="true"/></td>
								<tr>	
									<td>city:</td>
									<td><form:input type="text" path="city" name="city" required="true"/></td>
								</tr>	
								<tr>
									<td>Street:</td>
									<td><form:input type="text" path="street" name="street" required="true"/></td>
								</tr>
								<tr>	
									<td>Postcode</td>
									<td><form:input type="text" path="postCode" name="postCode" required="true"/></td>	
								</tr>
							</table>
						
					</td>
					
					
					<!-- empty cell -->
					<td>
					</td>
					
					<td>
					<!--  table Contact-->
							<table>
								<tr>
									<td><h2>Contact</h2></td>							
								</tr>
								<tr>
									<td>Mobile:</td>
									<td><form:input type="text" path="phone" name="phone" required="true"/></td>
								</tr>
								<tr>
									<td>Email:</td>
									<td><form:input type="text" path="email" name="email" required="true"/></td>
								</tr>		
							</table>
					
				</tr>
				<tr>
				<td></td> <td><input type="submit" value="save"></td>
				</tr>
			</table>
		</form:form>
				
			
</body>
</html>