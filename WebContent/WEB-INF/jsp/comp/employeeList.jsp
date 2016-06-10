<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<spring:url value="/resources/asset/icon/" var="iconPath"/>

<div id="tableWrapper">
	<ul id="table-menu">
	<li class="sort">Name</li>
	<li class="sort">Email</li>
	<li class="sort">Gender</li>
	<li class="sort">At work</li>
	<li>Manage</li>
	</ul>
	
			<table id="employeesTab">
				
				
				<c:if test="${!empty employees}">
				<c:forEach items="${employees}" var="curEmployee">
					<tr class="employee-row" data="${curEmployee.id}">
						<td>
							<div class="view-table">${curEmployee.name}</div>
							<div class="edit-table" hidden>
								<input name="firstname" value="${curEmployee.firstName}" size="10"/>
								<input name="lastname" value="${curEmployee.lastName}" size="10"/>
							</div>
						</td>
						<td>
							<div class="view-table">${curEmployee.contact.email}</div>								
							<div class="edit-table" hidden>
								<input name="email" value="${curEmployee.contact.email}"/>
							</div>
						</td>
						<td>
							<div class="view-table"><img src="${iconPath.concat(curEmployee.sex == 'FEMALE' ? 'woman.png' : 'man.png')}" alt="${curEmployee.sex}"/></div>
							<div class="edit-table" hidden>
								<select name="sex">
									<option class="female" value="Female" ${curEmployee.sex == 'FEMALE' ? 'selected' : ''} >FEMALE</option>
									<option class="male" value="Male" ${curEmployee.sex == 'MALE' ? 'selected' : ''} >MALE</option>
								</select>
							</div>
						</td>
						<td class="status"><div><img src="${iconPath.concat('no.png')}" alt="NO"/></div></td>
						<td class="edit-delete">
							<div class="delete-emp"><img src="/EmployeeOrganizer/resources/asset/icon/deleteEmployee.png" alt="delete"></div>
							<div class="edit-emp"><img src="/EmployeeOrganizer/resources/asset/icon/edit-icon.png" alt="edit"></div>
						</td>
					</tr>
					<tr class="employee-shifts">
						<td colspan="5"><img src="/EmployeeOrganizer/resources/asset/icon/shifts.png" alt="shifts"></td>
					</tr>
					
				</c:forEach>
				</c:if>		
			</table>
</div>	