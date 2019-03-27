<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="cause">

    <h2><fmt:message key="causesInfo"/></h2>

    <br/>
		<h3 style="color:#ff5f5f;"><fmt:message key="name"/></h3>
			<h4><c:out value="${cause.name}"/></h4>
		<br/>
		<h3 style="color:#ff5f5f;"><fmt:message key="description"/></h3>
			<h4><c:out value="${cause.description}"/></h4>
			<br/>
		<h3 style="color:#ff5f5f;"><fmt:message key="budgetTarget"/></h3>
			<h4><c:out value="${cause.budgetTarget}"/></h4>
			<br/>
		<h3 style="color:#ff5f5f;"><fmt:message key="organization"/></h3>
			<h4><c:out value="${cause.organization}"/></h4>	
			<br/>
		<h3 style="color:#ff5f5f;"><fmt:message key="isClosed"/></h3>
			<h4><c:if test="${cause.isClosed }">
			<fmt:message key="True"/></c:if></h4>
			
			<h4><c:if test="${!cause.isClosed }">
			<fmt:message key="False"/></c:if></h4>
</petclinic:layout>
