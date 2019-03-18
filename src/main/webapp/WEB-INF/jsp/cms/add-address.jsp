<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="contact" >
                      	<fieldset class="form-group">
				<form:label path="streetAddress">Street Address</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="streetAddress"/>
                                <form:errors path="streetAddress" cssClass="text-warning"/>
			</fieldset>
                         <fieldset class="form-group">
				<form:label path="city">City</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="city"/>
                                <form:errors path="city" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="state">State</form:label> 
				<form:input  type="text" 
					class="form-control" path="state"/>
                                <form:errors path="state" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="zipCode">Zip Code</form:label> 
				<form:input  type="text" 
					class="form-control" path="zipCode"/>
                                <form:errors path="zipCode" cssClass="text-warning"/>
			</fieldset>                   
			<button type="submit" class="btn btn-success">Update</button>
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>