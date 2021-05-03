<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
function onDeleteClick(event) {
	
	var doDelete = confirm("Are you sure you want to delete this offer?");
	
	if (doDelet == false) {
		event.preventDefault();
	}
}

function onReady() {
	$("#delete").click(onDeleteClick);
}

$(document).ready(onReady)

</script>

<form:form action="${pageContext.request.contextPath}/docreate"
	method="post" modelAttribute="offer">
	<form:input type="hidden" name="offerId" path="offerId" />

	<table class="formtable">
		<tr>
			<td class="label">Text:</td>
			<td><form:textarea class="control" path="text" name="text"
					cols="10" rows="10"></form:textarea><br>
			<form:errors path="text" cssClass="error"></form:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<td><input class="control" value="Save Advert" type="submit"></td>
		</tr>

		<c:if test="${offer.offerId != 0}">
			<tr>
				<td class="label"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input id="delete" class="delete control" name="delete" value="Delete Advert"
					type="submit"></td>
			</tr>
		</c:if>
	</table>

</form:form>