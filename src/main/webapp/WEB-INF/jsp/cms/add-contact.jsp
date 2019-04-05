<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="myStaff" action="addContactToEvent">
                    <fieldset>
                    <table>
                            <tr><td>
                                    <form:radiobutton path="staff" items="${staffList}" />
                            </td></tr>               
                    </table>
            
                 <%--         <form:select path = "staffList">
                              <c:forEach items="${staffList}" var="staff"> 
                                <form:option value="${staff.firstName} ${staff.lastName}">
                                   ${staff.firstName} ${staff.lastName}
                                </form:option>
                              </c:forEach>
                        </form:select>     --%>
                    </fieldset>          
			<button type="submit" class="btn btn-success">Update</button>
		</form:form>
	</div>
<%@ include file="common/footer.jspf" %>