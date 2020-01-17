<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
            <form:form method="post" modelAttribute="user" >

                        <fieldset class="form-group">
				<form:label path="username">User Name</form:label> 
				<form:input  type="text" 
					class="form-control" path="username"/>
                                <form:errors path="username" cssClass="text-warning"/>
			</fieldset> 
                        <fieldset class="form-group">
				<form:label path="password">Password</form:label> 
				<form:input  type="text" 
					class="form-control" path="password"/>
                                <form:errors path="password" cssClass="text-warning"/>
			</fieldset> 

                <input type=hidden id="role" name="role">
                <button type="submit" class="btn btn-success">Update</button>
            </form:form>
	</div>

<%@ include file="common/footer.jspf" %>