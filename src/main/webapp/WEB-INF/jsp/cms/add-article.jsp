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
                        <fieldset class="form-group">
				<form:label path="pageName">Page Name(Will not be seen, used for internal reference)</form:label> 
				<form:input  type="text" 
					class="form-control" path="pageName"/>
                                <form:errors path="pageName" cssClass="text-warning"/>
			</fieldset>
			<button type="submit" class="btn btn-success">Update</button>
                        ${ArticalWebPage}
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>