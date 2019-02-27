<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Articles</caption>
			<thead>
				<tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Position</th>
                                    <th></th>
                                    <th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${staffers}" var="staff">
					<tr>
                                            <td>${staff.firstName}</td>
                                            <td>${staff.lastName}</td>                                                                                        
                                            <td>${staff.position}</td>
                                            <td><a type="button" class="btn btn-success" href="/update-staff?id=${staff.id}">Update</a></td>
                                            <td><a type="button" class="btn btn-warning" href="/delete-staff?id=${staff.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div> <a class="button" href="/add-staff">Add Staff</a></div>
<%@ include file="common/footer.jspf" %>

