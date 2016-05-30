<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${dialogTitle}</title>

</head>
<body>
	<ul class="items-list" data="${table}">
		<c:forEach items="${items}" var="curItem">
			<li>
				<div class="item-display">
					<label>${curItem.name}</label>
					<img class="editBut" alt="edit" src="/EmployeeOrganizer/resources/asset/icon/edit.png">
				</div>
				<div class="item-edit" hidden>				
					<input name="name" value="${curItem}" data="${curItem.id}"/>
					<img class="confirmBut" alt="confirm" src="/EmployeeOrganizer/resources/asset/icon/confirm.png">
				</div>							
			</li>
		</c:forEach>			
	</ul>
</body>
</html>