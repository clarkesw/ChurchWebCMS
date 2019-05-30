<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<form:form method="post" modelAttribute="sermon" >
                      	<fieldset class="form-group">
				<form:label path="title">Title</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="title"/>
                                <form:errors path="title" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="subTitle">Sub Title</form:label> 
				<form:textarea  type="text" 
					class="form-control" path="subTitle"/>
                                <form:errors path="subTitle" cssClass="text-warning"/>
			</fieldset>
			<fieldset class="form-group">
				<form:label path="description">Description</form:label> 
				<form:textarea  type="text" 
					class="form-control" path="description"/>
                                <form:errors path="description" cssClass="text-warning"/>
			</fieldset>
                        <fieldset class="form-group">
                            <form:label path="sermonDate">Sermon Date</form:label>
                                <table>
                                    <tr>
                                        <td>
                                            <form:input path="sermonDate" type="text" class="form-control"
                                                    required="required" />
                                            <form:errors path="sermonDate" cssClass="text-warning" />
                                        </td>
                                    </tr>
                                </table>     
			</fieldset>
                    <button type="submit" class="btn btn-success">Update</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>