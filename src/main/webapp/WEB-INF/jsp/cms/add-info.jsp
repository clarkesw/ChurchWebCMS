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
					class="form-control" path="missionStatement"/>
                                <form:errors path="missionStatement" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="email">Email</form:label> 
				<form:input  type="text" 
					class="form-control" path="email"/>
                                <form:errors path="email" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="telephone">Phone Number</form:label> 
				<form:input  type="text" 
					class="form-control" path="telephone"/>
                                <form:errors path="telephone" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="leadPastor">Lead Pastor</form:label> 
				<form:input  type="text" 
					class="form-control" path="leadPastor"/>
                                <form:errors path="leadPastor" cssClass="text-warning"/>
			</fieldset>
                       <fieldset class="form-group">
				<form:label path="associatePastor">Associate Pastor</form:label> 
				<form:input  type="text" 
					class="form-control" path="associatePastor"/>
                                <form:errors path="associatePastor" cssClass="text-warning"/>
			</fieldset>

                        <fieldset class="form-group">
				<form:label path="youthMinister">Youth Minister</form:label> 
				<form:input  type="text" 
					class="form-control" path="youthMinister"/>
                                <form:errors path="youthMinister" cssClass="text-warning"/>
			</fieldset>
                        <input type=hidden id="address" name="address">
			<button type="submit" class="btn btn-success">Update</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>