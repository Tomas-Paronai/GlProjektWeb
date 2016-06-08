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
	<form:form method="post" action="updatePage" modelAttribute="employee">
			<form:input type="hidden" path="id" value="${employee.id}" />
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
							<tr>
								<td>Sex:</td>
								<td> <input type="radio" name="gender" value="female"> Female
  									 <input type="radio" name="gender" value="male"> Male  </td>
							</tr>
						</table>
					</td>
					
					<td>
					<!-- empty cell -->
					</td>
					
					<!--address cell  -->
					<td>
					<!--  table address-->
						<table>
							<tr>
								<td><h2>Adrress</h2></td>							
							</tr>
							<tr>
								<td>country:</td>
								<td><form:input type="text" path="lastName" name="country" required="true"/></td>
							<tr>	
								<td>city:</td>
								<td><form:input type="text" path="lastName" name="city" required="true"/></td>
							</tr>	
							<tr>
								<td>Street:</td>
								<td><form:input type="text" path="lastName" name="street" required="true"/></td>
							</tr>
							<tr>	
								<td>Postcode</td>
								<td><form:input type="text" path="lastName" name="postCode" required="true"/></td>
							</tr>
						</table>
					</td>	
				</tr>
				
				<!-- second row salary & contact -->
				<tr>
					<td>
					<!--  table salary-->
						<table>
							<tr>
								<td><h2>Salary</h2></td>							
							</tr>
							<tr>
								<td>Wage per hour:</td>
								<td><form:input type="text" path="firstName" name="firstName" required="true"/></td>
							</tr>	
							</tr>
								<td>Contract:</td>
								<td><form:input type="text" path="firstName" name="firstName" required="true"/></td>
							</tr>
							<tr>	
								<td>Position:</td>
								<td><form:input type="text" path="firstName" name="firstName" required="true"/></td>
							
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
								<td><h2>Salary</h2></td>							
							</tr>
							<tr>
								<td>Wage per hour:</td>
								<td><form:input type="text" path="firstName" name="firstName" required="true"/></td>
							</tr>
							<tr>
								<td>Contract:</td>
								<td><form:input type="text" path="firstName" name="firstName" required="true"/></td>
							</tr>	
							<tr>
								<td>Position:</td>
								<td><form:input type="text" path="firstName" name="firstName" required="true"/></td>
							
							</tr>
						</table>
					</td>
					
					<!-- empty cell -->
					<td>
				
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" value="Save" />
						<input type="button" value="Back" id="backToBrands" class="cancel-button" />
					</td>
				</tr>
			</table>
		</form:form>

</body>
</html>