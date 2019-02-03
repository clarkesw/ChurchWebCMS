<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="info" >
                      	<fieldset class="form-group">
				<form:label path="name">Name</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="name"/>
                                <form:errors path="name" cssClass="text-warning"/>
			</fieldset>
                         <fieldset class="form-group">
				<form:label path="missionStatement">Mission Statement</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="missionStatement"/>
                                <form:errors path="missionStatement" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="content">Content</form:label> 
				<form:textarea  type="text" 
					class="form-control" path="content"/>
                                <form:errors path="content" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="imageURL">Image URL</form:label> 
				<form:input  type="text" 
					class="form-control" path="imageURL"/>
                                <form:errors path="imageURL" cssClass="text-warning"/>
			</fieldset>
			<button type="submit" class="btn btn-success">Update</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>