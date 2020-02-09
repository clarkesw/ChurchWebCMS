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
                            <table border="1" cellpadding="10">
                                <thead>
                                    <tr>
                                        <th width="100">Book</th>
                                        <th width="100">Chapter</th>
                                        <th width="100">Verse </th>
                                        <th width="100"> </th>
                                    </tr>
                                </thead>
                                <c:if test="${not empty passageList}">
                                    <c:forEach items="${passageList}" var="pass">
                                        <tr>
                                            <td>${pass.book}</td>
                                            <td>${pass.chapter}</td>
                                            <td>${pass.verse}</td>
                                            <td>
                                                <input id="myInput" value="${pass.link}">
                                                <button onclick="copyLinkFunc()">Copy Link</button>
                                            </td>
                                        </tr>   
                                    </c:forEach>
                                </c:if> 
                            </table>  
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