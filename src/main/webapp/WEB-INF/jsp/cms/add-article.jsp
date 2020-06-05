<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="article" >
                      	<fieldset class="form-group">
				<form:label path="title">Title</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="title"/>
                                <form:errors path="title" cssClass="text-warning"/>
			</fieldset>
                         <fieldset class="form-group">
				<form:label path="subTitle">SubTitle</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="subTitle"/>
                                <form:errors path="subTitle" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="imageURL">Image URL</form:label> 
				<form:input  type="text" 
					class="form-control" path="imageURL"/>
                                <form:errors path="imageURL" cssClass="text-warning"/>
			</fieldset>
                        <form:hidden path="content"/>
			<button type="submit" class="btn btn-success">Update</button>
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>