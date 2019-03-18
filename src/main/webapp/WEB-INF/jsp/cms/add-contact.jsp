<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="updateContact" >
                    <fieldset>
                        <form:select path = "contact">
                         <%--  <form:option value = "NONE" label = "Select a Contact"/> --%>
                              <c:forEach items="${staffList}" var="staff"> 
                                <form:option value="${staff.firstName} ${staff.lastName}"/>

                       
                              </c:forEach>
                         <%--   <form:options items = "${staffList}" />--%>
                        </form:select>   
                    </fieldset>          
			<button type="submit" class="btn btn-success">Update</button>
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>