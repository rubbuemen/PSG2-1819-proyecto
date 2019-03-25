<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="donations">


     <jsp:attribute name="customScript"> 
       <script> -->
            $(function () {
                 $("#date").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script> 
  </jsp:attribute> 
    <jsp:body>
    <h2>
        <c:if test="${donation['new']}"><fmt:message key="newDonation"/> </c:if>
    </h2>
    <form:form modelAttribute="donation" class="form-horizontal" id="add-donation-form">
        <div class="form-group has-feedback">
        
        	<div class="form-group">
                    <label class="col-sm-2 control-label"><fmt:message key="cause"/></label>
                    <div class="col-sm-10">
                        <c:out value="${donation.cause.name}"/>
                    </div>
             </div>
<!--              <div class="form-group"> -->
<%--                     <label class="col-sm-2 control-label"><fmt:message key="date"/></label> --%>
<!--                     <div class="col-sm-10"> -->
<%--                         <c:out value="${donation.date}"/> --%>
<!--                     </div> -->
<!--              </div> -->
      		<fmt:message var="amount" key="amount"/>
      		<fmt:message var="client" key="client"/>
      		<fmt:message var="date" key="date"/>
            <petclinic:inputField label="${date}" name="date"/>
            <petclinic:inputField label="${amount}" name="amount"/>
            <petclinic:inputField label="${client}" name="client"/>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <c:choose>
                    <c:when test="${donation['new']}">
                        <button class="btn btn-default" type="submit"><fmt:message key="addDonation"/></button>
                    </c:when>
                </c:choose>
            </div>
        </div>
    </form:form>
    </jsp:body>
</petclinic:layout>