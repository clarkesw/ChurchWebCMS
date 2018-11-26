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
                            <form:label path="startDate">Start Date</form:label>
                                <table>
                                    <tr>
                                        <td>
                                            <form:input path="startDate" type="text" class="form-control"
                                                    required="required" />
                                            <form:errors path="startDate" cssClass="text-warning" />
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
				<form:label path="endDate">End Date</form:label>
                                <table>
                                    <tr>
                                        <td>
                                            <form:input path="endDate" type="text" class="form-control"
                                                    required="required" />
                                            <form:errors path="endDate" cssClass="text-warning" />
                                        </td>
                                        <td>
                                            <form:input path="endTime" type="text" class="form-control"
                                                    required="required" />
                                            <form:errors path="endTime" cssClass="text-warning" />     
                                        </td>
                                    </tr>
                                </table>
				
			</fieldset>
                     <fieldset class="form-group">
				<form:label path="url">Image URL</form:label> 
				<form:input  type="text" 
					class="form-control" path="url"/>
                                <form:errors path="url" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="isRepeated">Is Event Repeated?</form:label> 
				<form:checkbox class="form-control"  path="isRepeated"/>
                                <form:errors path="isRepeated" cssClass="text-warning"/>
			</fieldset>
			<button type="submit" class="btn btn-success">Add</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>