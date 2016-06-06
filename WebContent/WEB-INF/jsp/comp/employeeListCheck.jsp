<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select employees</title>

<spring:url value="/resources/css/jquery-ui.css" var="dialogUiStyle"/>
<link rel="stylesheet" type="text/css" href="${dialogUiStyle}" />

<spring:url value="/resources/js/jquery-2.2.4.min.js" var="lib"/>
<script type="text/javascript" src="${lib}"></script>

<spring:url value="/resources/js/jquery-ui.min.js" var="uiLib"/>
<script type="text/javascript" src="${uiLib}"></script>

<spring:url value="/resources/js/dialogManage.js" var="dialogJs"/>
<script type="text/javascript" src="${dialogJs}"></script>

</head>
<body>			
		<table>
			<tr>
				<th>Name</th>
				<th>Email</th>
			</tr>	
			
			
			<c:if test="${!empty employeesCheck}">
			<c:forEach items="${employeesCheck}" var="curEmployee">
				<tr class="checkBoxEmployee">
					<td>${curEmployee.name}<div class="person-mail" hidden>${curEmployee.contact.email}</div></td>
					<td><input type="checkbox" class="list-checkbox" name="empId" value="${curEmployee.id}"/></td>
				</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td><input class="select-all" type="checkbox">All</td> 
				</tr>
				</c:if>
			
			
					
		</table>
	</body>

</html>