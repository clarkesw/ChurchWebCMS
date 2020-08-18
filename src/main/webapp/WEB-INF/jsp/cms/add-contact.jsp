<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="staffer" action="/api/addContactToEvent">
<%--                    <fieldset>
                        <table>
                            <tr><td>
                                    <form:radiobutton path="firstName" items="${staffList}" />
                            </td></tr>               
                        </table>
                    </fieldset>          --%>
                  <form:select path = "fullName">
                     <form:option value = "NONE" label = "Select"/>
                     <form:options items = "${staffList}"  itemLabel="fullName"/>
                  </form:select>  
			<button type="submit" class="btn btn-success">Update</button>
		</form:form>
	</div>
<%@ include file="common/footer.jspf" %>