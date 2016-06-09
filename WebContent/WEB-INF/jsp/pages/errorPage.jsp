<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request failed</title>

<spring:url value="/resources/js/jquery-2.2.4.min.js" var="lib"/>
<script type="text/javascript" src="${lib}"></script>

<spring:url value="/resources/js/errorpage.js" var="errJs"/>
<script type="text/javascript" src="${errJs}"></script>
</head>
<body>
	<h1 align="center">${message}</h1>
	<div align="center">You will be redirected back in 8 seconds.</div><br>
	<p goto="${next}">${exception}</p>
</body>
</html>