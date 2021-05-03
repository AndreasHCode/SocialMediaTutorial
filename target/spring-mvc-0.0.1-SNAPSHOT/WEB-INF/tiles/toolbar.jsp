<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>

<c:choose>
	<c:when test="${hasOffer}">
		<a href="${pageContext.request.contextPath}/createoffer">Edit your current Offer</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath}/createoffer">Create New Offer</a>
	</c:otherwise>
</c:choose>
&nbsp;
<sec:authorize access="isAuthenticated()">
	<a href="${pageContext.request.contextPath}/messages">Messages (<span id="numberMessages"></span>)</a>
</sec:authorize>

<br>
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<p><a href="<c:url value='/admin' />">Admin Page</a></p>
</sec:authorize>