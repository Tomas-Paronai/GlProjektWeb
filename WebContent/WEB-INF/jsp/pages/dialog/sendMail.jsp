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
</head>
<body>
	<form:form action="sendMail" method="POST" modelAttribute="mailFormBean">
		<table>
			<tr>
				<th>Recipents</th>
			</tr>
			<tr>
				<td colspan="2">
					<form:input path="recipent" placeholder="Devide recipents with ;" value="${!empty recipents ? recipents : ''}"/>
				</td>
				<td></td>
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