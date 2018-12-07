<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="page" >
                      	<fieldset class="form-group">
				<form:label path="pageName">Page Title</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="pageName"/>
                                <form:errors path="pageName" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="message">Pastor's Message</form:label> 
				<form:textarea  type="text" 
					class="form-control" path="message"/>
                                <form:errors path="message" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="announcements">Announcements</form:label> 
				<form:textarea  type="text" 
					class="form-control" path="announcements"/>
                                <form:errors path="announcements" cssClass="text-warning"/>
			</fieldset>
                     <fieldset class="form-group">
				<form:label path="mainPic">Main Image</form:label> 
				<form:input  type="text" 
					class="form-control" path="mainPic"/>
                                <form:errors path="mainPic" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="isVisible">Is Visible?</form:label> 
				<form:checkbox class="form-control"  path="isVisible"/>
                                <form:errors path="isVisible" cssClass="text-warning"/>
			</fieldset>
			<button type="submit" class="btn btn-success">Add</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>