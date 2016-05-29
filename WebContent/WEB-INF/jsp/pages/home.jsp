<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Organizer</title>
</head>
<body>
	<table>
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Gender</th>
			<th>In work</th>
		</tr>
		<c:set var="index" value="1"/>
		<c:forEach items="${employees}" var="curEmployee">
			<tr class="employee-row" row="${index}">
				<td>${curEmployee.name}</td>
				<td>${curEmployee.email}</td>
				<td>${curEmployee.sex}</td>
				<td>TODO</td>
			</tr>
		</c:forEach>		
	</table>
	
	<c:forEach items="${employees}" var="curEmployee">
	<c:set var="address" value="${curEmployee.address}"/>
	<c:set var="detail" value="${curEmployee.detail}"/>
	<div class="detail ${index}">
		<table>
		
			<!-- ADDRESS -->
			<tr>
				<td class="category" colspan="2">Address</td>
			</tr>
			<tr>
				<td class="cat-detail">Country:</td>
				<td class="cat-value">${address.country}</td>
			</tr>
			<tr>
				<td class="cat-detail">City:</td>
				<td class="cat-value">${address.city}</td>
			</tr>
			<tr>
				<td class="cat-detail">Street:</td>
				<td class="cat-value">${address.street}</td>
			</tr>
			<tr>
				<td class="cat-detail">Post-code:</td>
				<td class="cat-value">${address.postCode}</td>
			</tr>
			
			<!-- EMPLOYMENT DETAIL -->
			<tr>
				<td class="category" colspan="2">Employment detail</td>
			</tr>
			<tr>
				<td class="cat-detail">Position:</td>
				<td class="cat-value">${detail.position}</td>
			</tr>
			<tr>
				<td class="cat-detail">Contract:</td>
				<td class="cat-value">${detail.contract}</td>
			</tr>
			<tr>
				<td class="cat-detail">Wage:</td>
				<td class="cat-value">${detail.salary} $</td>
			</tr>
			<tr>
				<td class="cat-detail">Employed since:</td>
				<td class="cat-value">${detail.workSince} $</td>
			</tr>
			
		</table>
	</div>	
	<c:set var="index" value="${index+1}"/>
	</c:forEach>
	
</body>
</html>