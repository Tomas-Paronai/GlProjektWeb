<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Salary management</title>

<spring:url value="/resources/js/jquery-2.2.4.min.js" var="lib"/>
<script type="text/javascript" src="${lib}"></script>

<spring:url value="/resources/js/jquery-ui.min.js" var="uiLib"/>
<script type="text/javascript" src="${uiLib}"></script>

<spring:url value="/resources/css/jquery-ui.css" var="dialogUiStyle"/>
<link rel="stylesheet" type="text/css" href="${dialogUiStyle}" />

<spring:url value="/resources/js/dialogManage.js" var="dialogJs"/>
<script type="text/javascript" src="${dialogJs}"></script>

<spring:url value="/resources/js/salary.js" var="salaryJs"/>
<script type="text/javascript" src="${salaryJs}"></script>


<spring:url value="/resources/css/dialog.css" var="pageDialogStyle"/>
<link rel="stylesheet" type="text/css" href="${pageDialogStyle}" />

</head>
<body>
	<div class="top-panel">
		<select>
			<c:forEach items="${employees}" var="curEmployee" varStatus="index">
				<option value="${curEmployee.id}" ${index == 0 ? selected : ''}>${curEmployee.name}</option>
			</c:forEach>
		</select>
		<div class="employee-salary">-</div>
	</div>
	<div class="table-container">
		<div>Select period:
			<select class="period">
				<option value="0">Last week</option>
				<option value="1">Last month</option>
				<option value="2">Last year</option>
				<option value="3">Total</option>
			</select>
		</div>
		<table class="shifts-table">
			<tr>
				<th>Enter time</th>
				<th>Exit time</th>
				<th>Hours</th>
			</tr>
			<!-- TOTAL HOURS LAST LINE class="total-hours" -->
		</table>
	</div>
	<div>Total wage sum: </div><div class="wage"></div>
</body>
</html>