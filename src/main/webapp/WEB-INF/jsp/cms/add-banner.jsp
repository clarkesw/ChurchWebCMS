<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="banner" >
                      	<fieldset class="form-group">
				<form:label path="message">Message</form:label> 
				<form:input  type="text" maxlength="200"
					class="form-control" required="required" path="message"/>
                                <form:errors path="message" cssClass="text-warning"/>
			</fieldset>               
			<button type="submit" class="btn btn-success">Update</button>
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>