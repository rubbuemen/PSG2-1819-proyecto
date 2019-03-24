<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="causes">
	<h2>
		<fmt:message key="causes" />
	</h2>
	
	<table id="causesTable" class="table table-striped">
		<thead>
			<tr>
				<th><fmt:message key="name" /></th>
				<th><fmt:message key="budgetAchieved"/></th>
				<th><fmt:message key="budgetTarget"/></th>
				<c:if test="${!cause.isClosed}">
				<th></th>
				<th></th>
				</c:if>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${causes}" var="cause">
				<tr>
					<td><spring:url value="/causes/{causeId}.html" var="causeUrl">
							<spring:param name="causeId" value="${cause.id}" />
						</spring:url>
						<c:out value="${cause.name}" /></td>
						<td><c:out value="${40}"/></td>
				<td><c:out value="${cause.budgetTarget}"/></td>
				<c:if test="${!cause.isClosed}">
				<td><spring:url value="donation/new/{causeId}" var="createUrl">
       			<spring:param name="causeId" value="${cause.id}"/>
    			</spring:url>
   				<a href="${fn:escapeXml(createUrl)}" class="btn btn-default"><fmt:message key="createDonation"/></a>
				</td>
				<td><spring:url value="/causes/{causeId}" var="detailsUrl">
       			<spring:param name="causeId" value="${cause.id}"/>
    			</spring:url>
   				<a href="${fn:escapeXml(detailsUrl)}" class="btn btn-default"><fmt:message key="causeDetails"/></a>
				</td>
				</c:if>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>

	<table class="table-buttons">
		<tr>
			<td><a class="btn btn-default" style="margin-right: 10px"
				href='<spring:url value="/causes/new" htmlEscape="true"/>'><fmt:message
						key="addCause" /></a></td>
		</tr>
	</table>
</petclinic:layout>