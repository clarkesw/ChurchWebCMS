<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
            
                 <table border="1" cellpadding="10">
                        <thead>
                            <tr>
                                <th width="100">Day</th>
                                <th width="100">Time</th>
                            </tr>
                        </thead>
                        <c:if test="${not empty serviceTimes}">
                            <c:forEach items="${serviceTimes}" var="times">
                                <tr>
                                    <td>${times.day}</td>
                                    <td>${times.time}</td>
                                </tr>   
                            </c:forEach>
                        </c:if> 
                    </table>  
                 <br><br><hr>
		<form:form method="post" modelAttribute="serviceTime" >
                        <fieldset class="form-group">
			    <form:label path="day">Day</form:label> 
                                <form:select path = "day">
                                <form:option value = "NONE" label = "Select"/>
                                <form:options items = "${days}"/>
                                </form:select> 
			</fieldset>   
                        
                        <fieldset class="form-group">
				<form:label path="time">Time</form:label> 
				<form:input  type="text" maxlength="25"
					class="form-control" required="required" path="time"/>
                                <form:errors path="time" cssClass="text-warning"/>
			</fieldset>   
			<button type="submit" class="btn btn-success">Update</button>
                        
		</form:form>
	</div>

<%@ include file="common/footer.jspf" %>