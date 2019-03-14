<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="vets">
	<h2>
		<fmt:message key="veterinarians" />
	</h2>

	<table id="vetsTable" class="table table-striped">
		<thead>
			<tr>
				<th><fmt:message key="name" /></th>
				<th><fmt:message key="specialties" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${vets.vetList}" var="vet">
				<tr>
					<td><spring:url value="/vets/{vetId}.html" var="vetUrl">
						<spring:param name="vetId" value="${vet.id}" />
						</spring:url> <a href="${fn:escapeXml(vetUrl)}"><c:out value="${vet.firstName} ${vet.lastName}" /></a></td>
						<td><c:forEach var="specialty" items="${vet.specialties}">
								<c:out value="${specialty.name} " />
							</c:forEach>
							<c:if test="${vet.nrOfSpecialties == 0}">none</c:if>
						</td>
						<td><a class="btn btn-default" href="/vets/${vet.id}/delete"><fmt:message key="deleteVet" /></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

	<table class="table-buttons">
		<tr>
			<td><a class="btn btn-default" style="margin-right: 10px"
				href='<spring:url value="/vets/new" htmlEscape="true"/>'><fmt:message
						key="addVet" /></a></td>
			<td><a style="margin-right: 10px"
				href="<spring:url value="/vets.xml" htmlEscape="true" />"><fmt:message
						key="viewAs" /> XML</a></td>
			<td><a style="margin-right: 10px"
				href="<spring:url value="/vets.json" htmlEscape="true" />"><fmt:message
						key="viewAs" /> JSON</a></td>
		</tr>
	</table>
</petclinic:layout>