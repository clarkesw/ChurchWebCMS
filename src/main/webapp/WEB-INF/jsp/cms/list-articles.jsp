<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Articles</caption>
			<thead>
				<tr>
                                    <th>Name</th>
                                    <th>Page</th>
                                    <th>Published Date</th>
                                    <th></th>
                                    <th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${articles}" var="art">
                                        <fmt:formatDate value="${art.publishedDate}" var="formattedLastModified" 
                                            type="date" pattern="MM-dd-yyyy hh:mm a" /> 
					<tr>
                                            <td>${art.title}</td>
                                            <td>${art.pageName}</td>                                                                                        
                                            <td>${formattedLastModified}</td>
                                            <td><a type="button" class="btn btn-success" href="/update-article?id=${art.id}">Update</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div> <a class="button" href="/add-articles">Add Article</a></div>
<%@ include file="common/footer.jspf" %>

