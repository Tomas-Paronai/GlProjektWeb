<%--
  Created by IntelliJ IDEA.
  User: tomas
  Date: 6/5/2016
  Time: 11:49 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
    <title>Export employees</title>

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
    <form:form action="export" method="post" modelAttribute="selectBean" class="export-form">
    <c:if test="${!empty employeesCheck}">
    <ul>
        <c:forEach items="${employeesCheck}" var="emp">
        	<li><form:checkbox class="list-checkbox" path="empId" value="${emp.id}"/>${emp.name}</li>
        </c:forEach>
        <li><input type="checkbox" class="select-all">All
        <li><form:button type="submit">Export</form:button></li>
    </ul>
    </c:if>
    </form:form>
</body>
</html>
