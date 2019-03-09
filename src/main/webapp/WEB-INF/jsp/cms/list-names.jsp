<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Web Page Names</caption>
			<thead>
				<tr>
                                    <th>Name</th>
                                    <th></th>
                                    <th></th>
				</tr>
			</thead>
			<tbody>
                            <c:forEach items="${names}" var="name">
                            <tr>
                                <td>${name.name}</td>
                                <td><a type="button" class="btn btn-success" href="/update-name?id=${name.id}">Update</a></td>
                                <c:if test="name.deletable">
                                    <td><a type="button" class="btn btn-warning" href="/delete-name?id=${name.id}">Delete</a></td>
                                </c:if>
                            </tr>
                            </c:forEach>
			</tbody>
		</table>
		<div><div> <a class="button" href="/add-names">Add Name</a></div>
<%@ include file="common/footer.jspf" %>

