<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Organizer</title>

<spring:url value="/resources/css/home.css" var="homeStyle"/>
<link rel="stylesheet" type="text/css" href="${homeStyle}"/>

<spring:url value="/resources/js/jquery-2.2.4.min.js" var="lib"/>
<script type="text/javascript" src="${lib}"></script>

<spring:url value="/resources/js/tabs.js" var="tabsjs"/>
<script type="text/javascript" src="${tabsjs}"></script>

<spring:url value="/resources/js/jquery-ui.min.js" var="uiLib"/>
<script type="text/javascript" src="${uiLib}"></script>

<spring:url value="/resources/css/jquery-ui.css" var="dialogUiStyle"/>
<link rel="stylesheet" type="text/css" href="${dialogUiStyle}" />

<spring:url value="/resources/js/dialogManage.js" var="dialogJs"/>
<script type="text/javascript" src="${dialogJs}"></script>

<spring:url value="/resources/asset/icon/" var="iconPath"/>
</head>
<body>

<div class="container">

	<ul class="tabs">
		<li class="tab-link current" data-tab="tab-1">Employees</li>
		<li class="tab-link" data-tab="tab-2">Actions</li>
	</ul>

	<div id="tab-1" class="tab-content current">
		<spring:url value="/resources/js/tableNavigation.js" var="tableScript"/>
	<script type="text/javascript" src="${tableScript}"></script>
	<table id="employeesTab">
		<tr>
			<th>Name</th>
			<th>Email</th>
			<th>Gender</th>
			<th>In work</th>
		</tr>

		<c:forEach items="${employees}" var="curEmployee">
			<tr class="employee-row" data="${curEmployee.id}">
				<td>${curEmployee.name}</td>
				<td>${curEmployee.email}</td>
				<td>
				<img src="${iconPath.concat(curEmployee.sex == 'FEMALE' ? 'woman.png' : 'man.png')}" alt="${curEmployee.sex}"/>
				</td>
				<td class="status"><img src="${iconPath.concat('no.png')}" alt="NO"/></td>
			</tr>
		</c:forEach>		
	</table>
	
	
	<div class="detail">
		<table id="detailInfo">		
			<!-- ADDRESS -->
			<tr>
				<td class="category" colspan="2">Address</td>
			</tr>
			<tr>
				<td class="cat-detail">Country:</td>
				<td class="cat-value" data="country">-</td>
			</tr>
			<tr>
				<td class="cat-detail">City:</td>
				<td class="cat-value" data="city">-</td>
			</tr>
			<tr>
				<td class="cat-detail">Street:</td>
				<td class="cat-value" data="street">-</td>
			</tr>
			<tr>
				<td class="cat-detail">Post-code:</td>
				<td class="cat-value" data="postcode">-</td>
			</tr>
			
			<!-- EMPLOYMENT DETAIL -->
			<tr>
				<td class="category" colspan="2">Employment detail</td>
			</tr>
			<tr>
				<td class="cat-detail">Position:</td>
				<td class="cat-value" data="position">-</td>
			</tr>
			<tr>
				<td class="cat-detail">Contract:</td>
				<td class="cat-value" data="contract">-</td>
			</tr>
			<tr>
				<td class="cat-detail">Wage/h:</td>
				<td class="cat-value" data="salary">-</td>
			</tr>
			<tr>
				<td class="cat-detail">Employed since:</td>
				<td class="cat-value" data="employed-since">-</td>
			</tr>
			
		</table>
	</div>		
	
	</div>
	<div id="tab-2" class="tab-content">
		<a class="openDialog" href="newData?item=position">Position</a>
		<a class="openDialog" href="newData?item=contract">Contract</a>
		 Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
	</div>

</div><!-- container -->

	
	
</body>
</html>