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
                                <c:forEach items="${events}" var="event">
                                        <fmt:formatDate value="${event.startDateCont}" var="formattedStartDate" 
                                                type="date" pattern="MM-dd-yyyy hh:mm a" />
                                        <fmt:formatDate value="${event.endDateCont}" var="formattedEndDate" 
                                                type="date" pattern="MM-dd-yyyy hh:mm a" />
					<tr>
                                            <td>${event.title}</td>
                                            <td>${formattedStartDate}</td>
                                            <td>${formattedEndDate}</td>
                                            <td>${event.isRepeated}</td>
                                            <td><a type="button" class="btn btn-success" href="/update-event?id=${event.id-1}">Update</a></td>
                                            <td><a type="button" class="btn btn-warning" href="/delete-event?id=${event.id-1}">Delete</a></td>
					</tr>
                                    </c:forEach>
			</tbody>
		</table>
		<div> <a class="button" href="/add-events">Add Event</a></div>
<%@ include file="common/footer.jspf" %>

