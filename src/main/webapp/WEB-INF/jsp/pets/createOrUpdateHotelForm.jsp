<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="owners">
    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#startDate").datepicker({dateFormat: 'yy/mm/dd'});
                $("#endDate").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2><c:if test="${hotel['new']}"><fmt:message key="newRoom"/></c:if></h2>

        <b><fmt:message key="pet"/></b>
        <table class="table table-striped">
            <thead>
            <tr>
                <th><fmt:message key="name"/></th>
                <th><fmt:message key="birthDate"/></th>
                <th><fmt:message key="type"/></th>
                <th><fmt:message key="owner"/></th>
            </tr>
            </thead>
            <tr>
                <td><c:out value="${hotel.pet.name}"/></td>
                <td><petclinic:localDate date="${hotel.pet.birthDate}" pattern="yyyy/MM/dd"/></td>
                <td><c:out value="${hotel.pet.type.name}"/></td>
                <td><c:out value="${hotel.pet.owner.firstName} ${hotel.pet.owner.lastName}"/></td>
            </tr>
        </table>

        <form:form modelAttribute="hotel" class="form-horizontal">
            <div class="form-group has-feedback">
            	<fmt:message var="details" key="details"/>
            	<fmt:message var="startDate" key="startDate"/>
            	<fmt:message var="endDate" key="endDate"/>
                <petclinic:inputField label="${details}" name="details"/>
                <petclinic:inputField label="${startDate}" name="startDate"/>
                <petclinic:inputField label="${endDate}" name="endDate"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="petId" value="${hotel.pet.id}"/>
                    <button class="btn btn-default" type="submit"><fmt:message key="addRoom"/></button>
                </div>
            </div>
        </form:form>

        <br/>
        <b><fmt:message key="previousRoom"/></b>
        <table class="table table-striped">
            <tr>
            	<th><fmt:message key="details"/></th>
                <th><fmt:message key="startDate"/></th>
                <th><fmt:message key="endDate"/></th>
            </tr>
            <c:forEach var="hotel" items="${hotel.pet.hotels}">
                <c:if test="${!hotel['new']}">
                    <tr>
                     	<td><c:out value="${hotel.details}"/></td>
                        <td><petclinic:localDate date="${hotel.startDate}" pattern="yyyy/MM/dd"/></td>
                        <td><petclinic:localDate date="${hotel.endDate}" pattern="yyyy/MM/dd"/></td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </jsp:body>

</petclinic:layout>
