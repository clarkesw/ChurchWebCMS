<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="page" >
                        <fieldset class="form-group">
				<form:label path="pageName">Page Name(Will not be seen, used for internal reference)</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="pageName"/>
                                <form:errors path="pageName" cssClass="text-warning"/>
			</fieldset>   
                      	<fieldset class="form-group">
				<form:label path="title">Page Title (On Tab)</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="title"/>
                                <form:errors path="title" cssClass="text-warning"/>
			</fieldset> 
                        <fieldset class="form-group">
				<form:label path="pageHeader">Page Header</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="pageHeader"/>
                                <form:errors path="pageHeader" cssClass="text-warning"/>
			</fieldset>   
			<fieldset class="form-group">
				<form:label path="message">Message</form:label> 
				<form:textarea  type="text" 
					class="form-control" path="message"/>
                                <form:errors path="message" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="bgImage">Background Image</form:label> 
				<form:input  type="text" 
					class="form-control" path="bgImage"/>
                                <form:errors path="bgImage" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="isVisible">Is Visible?</form:label> 
				<form:checkbox class="form-control"  path="isVisible"/>
                                <form:errors path="isVisible" cssClass="text-warning"/>
			</fieldset>
			<button type="submit" class="btn btn-success">Update</button>
                        
		</form:form>
                        <a href="list-articles">Edit ${pageName} Articles</a>
	</div>

<%@ include file="common/footer.jspf" %>