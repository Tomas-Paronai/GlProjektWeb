<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert employee</title>

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
	<form:form action="insert" modelAttribute="employeeBean" method="POST">
		<table>
			<tr>
				<td>
					<div>Name:</div>
				</td>
				<td>
					<form:input path="firtsName" size="10" placeholder="First name"/><form:input path="lastName" size="10" placeholder="Last name"/>
				</td>
			</tr>
			<tr>
				<td>
					<div>Sex:</div>
				</td>
				<td>
					<form:select path="sex">
						<option class="female" value="Female" selected>FEMALE</option>
						<option class="male" value="Male">MALE</option>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>
					<div>Date of birth:</div>
				</td>
				<td>
					<form:input path="dob" size="11" placeholder="2016-05-14"/>
				</td>
			</tr>
			<tr>
				<td>
					<div>Phone:</div>
				</td>
				<td>
					<form:input path="phone" size="14"/>
				</td>
			</tr>
			<tr>
				<td>
					<div>Email:</div>
				</td>
				<td>
					<form:input path="email"/>
				</td>
			</tr>
			<tr>
				<td>
					<div>State:</div>
				</td>
				<td>
					<form:input path="country" size="10" placeholder="Country"/><form:input path="city" size="10" placeholder="city"/>
				</td>
			</tr>
			<tr>
				<td>
					<div>Street:</div>
				</td>
				<td>
					<form:input path="street" size="10" placeholder="Street 5"/><form:input path="postcode" size="8" placeholder="04xxx"/>
				</td>
			</tr>
			<tr>
				<td>
					<div>Position:</div>
				</td>
				<td>
					<form:select path="position">
						<c:forEach items="${positions}" var="pos">
							<option value="${pos.name}">${pos.name}</option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>
					<div>Contract:</div>
				</td>
				<td>
					<form:select path="contract">
						<c:forEach items="${contracts}" var="con">
							<option value="${con.name}">${con.name}</option>
						</c:forEach>
					</form:select>
				</td>
			</tr>
			<tr>
				<td>
					<div>Salary/h:</div>
				</td>
				<td>
					<form:input path="salary" size="10" placeholder="$/h"/>
				</td>
			</tr>
			<tr>
				<td>
					<div>Start date:</div>
				</td>
				<td>
					<form:input path="employedsince" size="11" placeholder="2016-05-14"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Save"/>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>