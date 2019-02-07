<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="event" >
                      	<fieldset class="form-group">
				<form:label path="title">Title</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="title"/>
                                <form:errors path="title" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="details">Description</form:label> 
				<form:textarea  type="text" 
					class="form-control" path="details"/>
                                <form:errors path="details" cssClass="text-warning"/>
			</fieldset>
 			<fieldset class="form-group">
                            <form:label path="startDateCont">Start Date</form:label>
                                <table>
                                    <tr>
                                        <td>
                                            <form:input path="startDateCont" type="text" class="form-control"
                                                    required="required" />
                                            <form:errors path="startDateCont" cssClass="text-warning" />
                                        </td>
                                        <td>
                                            <form:input path="startTime" type="text" class="form-control"
                                                    required="required" />
                                            <form:errors path="startTime" cssClass="text-warning" />     
                                        </td>
                                    </tr>
                                </table>     
			</fieldset>
                        <fieldset class="form-group">
		<form:label path="endDateCont">End Date</form:label>
                                <table>
                                    <tr>
                                        <td>
                                            <form:input path="endDateCont" type="text" class="form-control"
                                                    required="required" />
                                            <form:errors path="endDateCont" cssClass="text-warning" />
                                        </td>
                                        <td>
                                            <form:input path="endTime" type="text" class="form-control"
                                                    required="required" />
                                            <form:errors path="endTime" cssClass="text-warning" />     
                                        </td>
                                    </tr>
                                </table>
				
                     </fieldset>
<!-- Add Dropdown to select name -->
                        <fieldset class="form-group">
				<form:label path="isRepeated">Is Event Repeated?</form:label> 
				<form:checkbox class="form-control"  path="isRepeated"/>
                                <form:errors path="isRepeated" cssClass="text-warning"/>
			</fieldset>
			<button type="submit" class="btn btn-success">Update</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>