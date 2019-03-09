<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="inputname" >
                        <fieldset class="form-group">
				<form:label path="name">Page Name</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="name"/>
                                <form:errors path="name" cssClass="text-warning"/>
			</fieldset>   
			<button type="submit" class="btn btn-success">Update</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>