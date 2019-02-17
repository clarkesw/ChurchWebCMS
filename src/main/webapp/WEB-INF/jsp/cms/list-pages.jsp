<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Web Page</caption>
			<thead>
				<tr>
                                    <th>Page</th>
                                    <th>Last Modified</th>
                                    <th>Page Visible</th>
                                    <th></th>
                                    <th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${pages}" var="page">
					<tr>
                                            <td>${page.pageName}</td>
                                            <td>${page.lastModified}</td>
                                            <td>${page.isVisible}</td>
                                            <td><a type="button" class="btn btn-success" href="/update-page?id=${page.id}">Update</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div> <a class="button" href="/add-pages">Add Page</a></div> 
<%@ include file="common/footer.jspf" %>

