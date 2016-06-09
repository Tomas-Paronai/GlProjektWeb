<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table class="detailInfo">										
					<tr>
						<td class="cat-detail">Country:</td>
						<td><input class="edit-value" data="country"/></td>
					</tr>
					<tr>
						<td class="cat-detail">City:</td>
						<td><input class="edit-value" data="city"/></td>
					</tr>
					<tr>
						<td class="cat-detail">Street:</td>
						<td><input class="edit-value" data="street"/></td>
					</tr>
					<tr>
						<td class="cat-detail">Post-code:</td>
						<td><input class="edit-value" data="postcode"/></td>
					</tr>
					<tr>
						<td class="cat-detail">Phone:</td>
						<td><input class="edit-value" data="phone"/></td>
					</tr>
					</table>
					<h3>Employment detail</h3>
					<!-- EMPLOYMENT DETAIL -->
					<table class="detailInfo">
					<tr>
						<td class="cat-detail">Date of birth:</td>
						<td><input class="edit-value" data="dob"/></td>
					</tr>
					<tr>
						<td class="cat-detail">Position:</td>
						<td><select class="edit-value" data="position">
							<c:forEach items="${positions}" var="pos">
								<option value="${pos.name}">${pos.name}</option>
							</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="cat-detail">Contract:</td>
						<td><select class="edit-value" data="contract">
							<c:forEach items="${contracts}" var="con">
								<option value="${con.name}">${con.name}</option>
							</c:forEach>
						</select></td>
					</tr>
					<tr>
						<td class="cat-detail">Wage/h:</td>
						<td><input class="edit-value" data="salary" size="10"/><div>$</div></td>
					</tr>
					<tr>
						<td class="cat-detail">Employed since:</td>
						<td><input class="edit-value" data="employed-since"/></td>
					</tr>					
				</table>