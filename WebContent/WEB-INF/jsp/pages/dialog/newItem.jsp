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
	<ul class="items-list" data="${table}">
		<c:forEach items="${items}" var="curItem">
			<li>
				<div class="item-display">
					<label>${curItem.name}</label>
					<img class="editBut" alt="edit" src="/EmployeeOrganizer/resources/asset/icon/edit.png">
					<img class="deleteBut" alt="delete" src="/EmployeeOrganizer/resources/asset/icon/delete.svg">
				</div>
				<div class="item-edit" hidden>				
					<input name="name" value="${curItem.name}" data="${curItem.id}"/>
					<img class="confirmBut" alt="confirm" src="/EmployeeOrganizer/resources/asset/icon/confirm.png">
					<img class="cancelBut" alt="cancel" src="/EmployeeOrganizer/resources/asset/icon/cancel.png">
				</div>							
			</li>
		</c:forEach>
		<li>
			<img class="newBut" alt="add" src="/EmployeeOrganizer/resources/asset/icon/add.png">
		</li>			
	</ul>
</body>

</html>