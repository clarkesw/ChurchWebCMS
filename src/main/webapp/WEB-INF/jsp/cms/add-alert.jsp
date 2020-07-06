<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="alert" >
                        <fieldset class="form-group">
                                <form:label path="group">Group</form:label> 
                                <form:select path="group">
                                   <form:option value = "NONE" label = "Select"/>
                                   <form:options items = "${positions}" />
                                </form:select>                         
                        </fieldset>
                         <fieldset class="form-group">
				<form:label path="message">Message</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="message"/>
                                <form:errors path="message" cssClass="text-warning"/>
			</fieldset>                 
			<button type="submit" class="btn btn-success">Send Message</button>
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>