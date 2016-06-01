<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table>
			<tr>
				<th>Name</th>
				<th>Email</th>
			</tr>
	
			<c:forEach items="${employees}" var="curEmployee">
				<tr class="checkBoxEmployee">
					<td>${curEmployee.name}</td>
					<td>${curEmployee.email}</td>
					<td><input type="checkbox" name="employeeName" value="${curEmployee.id}"></td>
				</tr>
			</c:forEach>		
</table>