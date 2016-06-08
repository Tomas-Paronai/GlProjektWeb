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

<spring:url value="/resources/js/salary.js" var="salaryJs"/>
<script type="text/javascript" src="${salaryJs}"></script>


</head>
<body>
	<div class="salary-details">
	<div class="top-panel">
		<select class="employee-select">
			<c:forEach items="${employees}" var="curEmployee" varStatus="index">
				<option value="${curEmployee.id}" ${index.index == 0 ? "selected" : ""}>${curEmployee.name}</option>
			</c:forEach>
		</select> <!-- wage="${curEmployee.detail.salary}" -->
			<c:forEach items="${employees}" var="curEmployee" varStatus="index">
				<div hidden class="wage-div" emp="${curEmployee.id}">${curEmployee.detail.salary}</div>
			</c:forEach>
		<div class="employee-salary">-</div>
	</div>
	<div class="table-container">
		<div>Select period:
			<select class="period">
				<option value="0">Past year</option>
				<option value="1">Past month</option>
				<option value="2">Past week</option>
				<option value="3" selected>Last week</option>
				<option value="4">Last month</option>
				<option value="5">Last year</option>
				<option value="6">Total</option>
			</select>
		</div>		
	</div>
	<div>Total hours sum: </div><div class="wage"></div>
	<div>Total wage sum: </div><div class="wage"></div>
	</div>
</body>
</html>