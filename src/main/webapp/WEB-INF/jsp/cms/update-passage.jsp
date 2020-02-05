<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">

                    <table border="1" cellpadding="10">
                        <thead>
                            <tr>
                                <th width="100">Book</th>
                                <th width="100">Chapter</th>
                                <th>Verse </th>
                            </tr>
                        </thead>
            <br><br><hr>
            <form:form method="post" modelAttribute="passage" action="update-passage"> 
                <fieldset class="form-group">
                      <form:label path="book">Book</form:label> 
                      <form:select path="book">
                           <form:option value = "NONE" label = "Select"/>
                           <form:options items = "${books}" />
                      </form:select> 
              <%--            <form:input  type="text" maxlength="25"
                                class="form-control" required="required" path="book"/>
                        <form:errors path="book" cssClass="text-warning"/>--%>
                </fieldset>
                <fieldset class="form-group">
                        <form:label path="chapter">Chapter</form:label> 
                        <form:input  type="text" maxlength="25"
                                class="form-control" path="chapter"/>
                        <form:errors path="chapter" cssClass="text-warning"/>
                </fieldset>
                <fieldset class="form-group">
                        <form:label path="verse">Verse</form:label> 
                        <form:input  type="text" maxlength="25"
                                class="form-control" path="verse"/>
                        <form:errors path="verse" cssClass="text-warning"/>
                </fieldset>          
                        <button type="submit" class="btn btn-success">Update</button>
            </form:form>
	</div>
<%@ include file="common/footer.jspf" %>