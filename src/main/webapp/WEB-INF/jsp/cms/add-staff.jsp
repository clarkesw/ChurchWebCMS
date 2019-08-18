<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="staff" >
                      	<fieldset class="form-group">
				<form:label path="firstName">First Name</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="firstName"/>
                                <form:errors path="firstName" cssClass="text-warning"/>
			</fieldset>
                         <fieldset class="form-group">
				<form:label path="lastName">Last Name</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="lastName"/>
                                <form:errors path="lastName" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="position">Position</form:label> 
				<form:input  type="text" 
					class="form-control" path="position"/>
                                <form:errors path="position" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="workEmail">Work Email</form:label> 
				<form:input  type="text" 
					class="form-control" path="workEmail"/>
                                <form:errors path="workEmail" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="homeEmail">Home Email</form:label> 
				<form:input  type="text" 
					class="form-control" path="homeEmail"/>
                                <form:errors path="homeEmail" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="workPhone">Work Phone</form:label> 
				<form:input  type="text" 
					class="form-control" path="workPhone"/>
                                <form:errors path="workPhone" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
				<form:label path="homePhone">Home Phone</form:label> 
				<form:input  type="text" 
					class="form-control" path="homePhone"/>
                                <form:errors path="homePhone" cssClass="text-warning"/>
			</fieldset>
                        
                    <c:if test = "${not empty unlockRole}">
                        <fieldset class="form-group">
				<form:label path="user">User Name</form:label> 
				<form:input  type="text" 
					class="form-control" path="user"/>
                                <form:errors path="user" cssClass="text-warning"/>
			</fieldset> 
                        <fieldset class="form-group">
				<form:label path="password">Password</form:label> 
				<form:input  type="text" 
					class="form-control" path="password"/>
                                <form:errors path="password" cssClass="text-warning"/>
			</fieldset> 
                        <form:select path = "role">
                           <form:option value = "NONE" label = "Select"/>
                           <form:options items = "${roles}"  itemLabel="role"/>
                        </form:select>  
                    </c:if>
			<button type="submit" class="btn btn-success">Update</button>
		</form:form>
                    <c:choose>
                        <c:when test="${staff.firstName ne null}">
                            <a href="updateAddressForStaff?fisrtName=${staff.firstName}&lastName=${staff.lastName}">
                                Edit ${staff.firstName}'s Address</a>
                        </c:when>
                         <c:otherwise>
                             <a href="addAddressForStaff?fisrtName=${firstName}&lastName=${lastName}">
                                Add ${firstName}'s Address</a>                            
                         </c:otherwise>
                    </c:choose> 
	</div>

<%@ include file="common/footer.jspf" %>