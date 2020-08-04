<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Articles</caption>
			<thead>
				<tr>
                                    <th>Title</th>
                                    <th>Last Modified Date</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${articles}" var="art">
					<tr>
                                            <td>${art.title}</td>                                                                                    
                                            <td>${art.lastModified}</td>
                                            <c:choose>
                                                <c:when test="${empty art.content}">
                                                    <td><a type="button" class="btn btn-success" 
                                                          href="/api/addDescriptionToArticle?id=${art.id}">Add Description</a></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><a type="button" class="btn btn-success" 
                                                          href="/api/addDescriptionToArticle?id=${art.id}">Edit Description</a></td>                                     
                                                </c:otherwise>
                                            </c:choose> 
                                            <td><a type="button" class="btn btn-success" href="/api/update-article?id=${art.id}">Update</a></td>
                                            <td><a type="button" class="btn btn-warning" href="/api/delete-article?id=${art.id}">Delete</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div> <a class="button" href="/api/add-articles">Add Article</a></div>
<%@ include file="common/footer.jspf" %>

