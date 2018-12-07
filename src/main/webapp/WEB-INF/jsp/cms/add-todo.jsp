<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="todo" >
			<fieldset class="form-group">
				<form:label path="desc">Description</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="desc"/>
                                <form:errors path="desc" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="targetDatePicker">Target Date</form:label>
				<form:input path="targetDatePicker" type="text" class="form-control"
					required="required" />
				<form:errors path="targetDatePicker" cssClass="text-warning" />
			</fieldset>
			<button type="submit" class="btn btn-success">Add</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>