<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h2>Create new Account</h2>

<form:form id="details" action="${pageContext.request.contextPath}/createaccount" method="post" modelAttribute="user">

<table class="formtable">
<tr><td class="label">Name: </td><td><form:input class="control" path="name" name="username" type="text" /><br><div class="error" ><form:errors path="name"></form:errors></div></td></tr>
<tr><td class="label">Email: </td><td><form:input class="control" path="email" name="email" type="text" /><br><div class="error" ><form:errors path="email" ></form:errors></div></td></tr>
<tr><td class="label">Password: </td><td><form:input id="password" class="control" path="password" name="password" type="password" /><br><div class="error" ><form:errors path="password" ></form:errors></div></td></tr>
<tr><td class="label">Confirm Password: </td><td><input id="confirm" class="control" name="passwordConfirm" type="password" /><div id="matchPass"></div></td></tr>
<tr><td class="label"></td><td><input class="control" name="Create Account" type="submit"></td></tr>
</table>

</form:form>