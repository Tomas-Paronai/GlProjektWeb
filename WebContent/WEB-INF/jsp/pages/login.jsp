<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Login Page</title>

<spring:url value="/resources/css/home.css" var="homeStyle"/>
<link rel="stylesheet" type="text/css" href="${homeStyle}"/>

<spring:url value="/resources/js/jquery-2.2.4.min.js" var="lib"/>
<script type="text/javascript" src="${lib}"></script>
</head>
<body>
	<div class="login-container">
		<div class="login">
			<h1 class="login-heading">Login</h1>
			<form:form method="post" action="doLogin" modelAttribute="command" class="loginForm">
			 	<form:input path="userName" class="input-txt" placeholder="Username"/>
			 	<form:password path="password" class="input-txt" placeholder="Password"/>
				<div class="login-footer">
					<button type="submit" class="btn btn--right">Sign in  </button>
					<br><a href="#" class="lnk">
              			I've forgotten something
            		</a>            		
				</div>
			</form:form>	
		</div>
	</div>
</body>
</html>