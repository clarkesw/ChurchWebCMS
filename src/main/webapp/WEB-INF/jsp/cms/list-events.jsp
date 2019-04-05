<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
                    <table class="table table-striped">
                        <caption>Calendar Events</caption>
                        <thead>
		<tr>
                                    <th>Title</th>
                                    <th>Start Date</th>
                                    <th>End Date</th>
                                    <th>Is Event Repeated?</th>
                                    <th></th>
                                    <th></th>
		</tr>
		</thead>
			<tbody>
                            <c:if test="${not empty events}">
                                <c:forEach items="${events}" var="event">
                                        <fmt:formatDate value="${event.startDateCont}" var="formattedStartDate" 
                                                type="date" pattern="MM-dd-yyyy" />
                                        <fmt:formatDate value="${event.endDateCont}" var="formattedEndDate" 
                                                type="date" pattern="MM-dd-yyyy" />
					<tr>
                                            <td>${event.title}</td>
                                            <td>${formattedStartDate} ${event.startTime}</td>
                                            <td>${formattedEndDate} ${event.endTime}</td>
                                            <td>${event.isRepeated}</td>
                                            <td><a type="button" class="btn btn-success" href="/update-event?id=${event.id}">Update</a></td>
                                            
                                             <c:choose>
                                                <c:when test="${empty event.contact}">
                                                    <td><a type="button" class="btn btn-success" 
                                                          href="addContactToEvent?contactId=-1&id=${event.id}"> ${event.contact}Add Contact</a></td>
                                                </c:when>
                                                <c:otherwise>
                                                     <td><a type="button" class="btn btn-success" 
                                                          href="addContactToEvent?contactId=${event.contact.id}&id=${event.id}">${event.contact.id} Edit Contact</a></td>                                       
                                                </c:otherwise>
                                            </c:choose> 
                                            <td><a type="button" class="btn btn-warning" href="/delete-event?id=${event.id}">Delete</a></td>
					</tr>
                                    </c:forEach>
                            </c:if>
			</tbody>
		</table>
		<div> <a class="button" href="/add-events">Add Event</a></div>
<%@ include file="common/footer.jspf" %>

