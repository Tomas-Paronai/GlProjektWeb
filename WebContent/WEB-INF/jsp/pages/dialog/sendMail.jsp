<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send mail</title>

<spring:url value="/resources/css/home.css" var="homeStyle"/>
<link rel="stylesheet" type="text/css" href="${homeStyle}"/>

<spring:url value="/resources/js/jquery-2.2.4.min.js" var="lib"/>
<script type="text/javascript" src="${lib}"></script>

<spring:url value="/resources/js/jquery-ui.min.js" var="uiLib"/>
<script type="text/javascript" src="${uiLib}"></script>

<spring:url value="/resources/css/jquery-ui.css" var="dialogUiStyle"/>
<link rel="stylesheet" type="text/css" href="${dialogUiStyle}" />

<spring:url value="/resources/js/dialogManage.js" var="dialogJs"/>
<script type="text/javascript" src="${dialogJs}"></script>

<spring:url value="/resources/css/dialog.css" var="pageDialogStyle"/>
<link rel="stylesheet" type="text/css" href="${pageDialogStyle}" />

<spring:url value="/resources/js/mailForm.js" var="mailJs"/>
<script type="text/javascript" src="${mailJs}"></script>

</head>
<body>
	<form:form action="sendMail" method="POST" modelAttribute="mailFormBean">
		<table>
			<tr>
				<th>Recipents</th>
			</tr>
			<tr>
				<td colspan="2">
					<form:textarea class="recipents-field" path="recipents" placeholder="Devide recipents with ;"/>
				</td>
				<td></td>
			</tr>
			<tr class="employees-select">
				<td colspan="2"><img src="/EmployeeOrganizer/resources/asset/icon/shifts.png" alt="employees"></td>
			</tr>
			<tr>
				<th>Subject</th>
			</tr>
			<tr>
				<td colspan="2">
					<form:input path="subject"/>
				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="2">
					<form:textarea path="message" placeholder="Type message here..."/>
				</td>
				<td></td>
			</tr>
		</table>
	</form:form>
</body>
</html>