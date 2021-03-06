<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<table class="offerstable">
<c:forEach var="offer" items="${offers}">

<tr class="offerrow">
<td class="name"><c:out value="${offer.user.name}"></c:out></td>
<td class="email"><a href="<c:url value='/message?uid=${offer.user.userId}' />" >${offer.user.email}</a></td>
<td class="text"><c:out value="${offer.text}"></c:out></td>
</tr>

</c:forEach>
</table>

<script type="text/javascript">

function updateMessageLink(data) {
	$("#numberMessages").text(data.number);
}

function startUp() {
	updatePage();
	window.setInterval(updatePage, 5000);
}

function updatePage() {
	
	$.getJSON("<c:url value='/getmessages'/>", updateMessageLink);
}

$(document).ready(startUp);

</script>