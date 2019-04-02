<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="vets">

    <h2><fmt:message key="vetsInfo"/></h2>


    <table class="table table-striped">
        <tr>
            <th><fmt:message key="name"/></th>
            <td><strong><c:out value="${vet.firstName} ${vet.lastName}"/></strong></td>
        </tr>
         <tr>
            <th><fmt:message key="specialties"/></th>
            <td><c:out value="${vet.specialties}"/></td>
        </tr>
    </table>

    <spring:url value="{vetId}/edit.html" var="editUrl">
        <spring:param name="vetId" value="${vet.id}"/>
    </spring:url>
    <a href="${fn:escapeXml(editUrl)}" class="btn btn-default"><fmt:message key="editVet"/></a>

<%-- <spring:url value="{vetId}/pets/new.html" var="addUrl"> --%>
<%--    <spring:param name="vetId" value="${vet.id}"/> --%>
<%-- </spring:url> --%>
<%-- <a href="${fn:escapeXml(addUrl)}" class="btn btn-default"><fmt:message key="addNewSpecialty"/></a> --%>

    <br/>
    <br/>
    <br/>

</petclinic:layout>
