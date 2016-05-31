<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

	<table>
		<tr>
			<th>Enter time</th>
			<th>Exit time</th>
			<th>Hours sum</th>
		</tr>
		<c:forEach items="${shifts}" var="shift">
			<tr>
				<td>${shift.enterTime}</td>
				<td>${shift.exitTime}</td>
				<td>${shift.totalHours}</td>
			</tr>
		</c:forEach>
	</table>
