<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table class="formtable">
<tr><td>Name</td><td>Email</td><td>Authority</td><td>Enabled</td></tr>
<c:forEach var="user" items="${users}">
	<tr><td><c:out value="${user.name}" /></td>
	<td><c:out value="${user.email}" /></td>
	<td><c:out value="${user.authority}" /></td>
	<td><c:out value="${user.enabled}" /></td></tr>
</c:forEach>

</table>
