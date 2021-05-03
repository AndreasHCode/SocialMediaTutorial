<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<h2>Send Message</h2>

<form:form id="details" method="post" modelAttribute="message" >
	
	<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}" />
	<input type="hidden" name="_eventId" value="send" />

	<table class="formtable">
		<tr>
			<td class="label">Your Name:</td>
			<td><form:input class="control" path="name" name="name" value="${fromName}"
					type="text" /><br>
			<div class="error">
					<form:errors path="name"></form:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Your Email:</td>
			<td><form:input class="control" path="email" name="email" value="${fromEmail}"
					type="text" /><br>
			<div class="error">
					<form:errors path="email"></form:errors>
				</div></td>
		</tr>	
		<tr>
			<td class="label">Subject:</td>
			<td><form:input class="control" path="subject" name="subject"
					type="text" /><br>
			<div class="error">
					<form:errors path="subject"></form:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label">Content:</td>
			<td><form:textarea class="control" path="content" name="content"
					type="text" /><br>
			<div class="error">
					<form:errors path="content"></form:errors>
				</div></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" name="Send Message" type="submit"></td>
		</tr>
	</table>

</form:form>