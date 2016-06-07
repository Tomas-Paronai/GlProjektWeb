<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Actions</title>
</head>
<body>
	<table class="actions">
		<tr>
			<td>
			<div class="action-bg">
				<img src="">
				<button class="openDialog btn btn-info btn-lg" data="newData?item=position">Position</button>
			</div>
			</td>
			<td>
			<div class="action-bg">
				<img src="">
				<button class="openDialog btn btn-info btn-lg" data="newData?item=contract">Contract</button>
			</div>
			</td>
			<td>
			<div class="action-bg">
				<img src="">
				<button class="openDialog btn btn-info btn-lg" data="mailForm">Send email</button>
			</div></td>
		</tr>
		<tr>
			<td>
			<div class="action-bg">
				<img src="">
				<button class="openDialog btn btn-info btn-lg" data="export">Export</button>
			</div>
			</td>
			<td>
			<div class="action-bg">
				<img src="">
				<button class="openDialog btn btn-info btn-lg" data="import">Import</button>
			</div>
			</td>
			<td>
			<div class="action-bg">
				<img src="">
			</div>
			</td>
		</tr>
	</table>
</body>
</html>