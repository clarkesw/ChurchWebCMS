<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">

                    <table border="1" cellpadding="10">
                        <thead>
                            <tr>
                                <th width="100">Book</th>
                                <th width="100">Chapter</th>
                                <th width="100">Verse </th>
                                <th width="100"> </th>
                                <th width="100"> </th>
                            </tr>
                        </thead>
                   <c:if test="${not empty passageList}">
                            <c:forEach items="${passageList}" var="pass">
                                <tr>
                                    <td>${pass.book}</td>
                                    <td>${pass.chapter}</td>
                                    <td>${pass.verse}</td>
                                    <td><a href="update-passage?id=${pass.id}">Edit</a></td>
                                    <td><a href="delete-passage?id=${pass.id}">Delete</a></td>
                                </tr>   
                            </c:forEach>
                        </c:if> 
                    </table>  
            <br><br><hr>
            <form:form method="post" modelAttribute="passage" action="/api/addPassagesToSermon"> 
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
                <c:choose>
                    <c:when test="${not empty passageList}">
                        <button type="submit" class="btn btn-success">Add</button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-success">Update</button>
                    </c:otherwise>
                </c:choose> 
            </form:form>
	</div>
<%@ include file="common/footer.jspf" %>