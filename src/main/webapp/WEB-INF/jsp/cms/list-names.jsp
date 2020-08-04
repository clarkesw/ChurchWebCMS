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
                                <td><a type="button" class="btn btn-success" href="/api/update-name?id=${name.id}">Update</a></td>
                             <%--   <c:if test="${name.deletable}">
                                    <td><a type="button" class="btn btn-warning" href="/delete-name?id=${name.id}">Delete</a></td>
                                </c:if> --%>
                                <c:choose>
                                    <c:when test="${name.deletable}">
                                        <td><a type="button" class="btn btn-warning" href="/api/delete-name?id=${name.id}">Delete</a></td>
                                    </c:when>    
                                    <c:otherwise>
                                        <td></td>
                                    </c:otherwise>
                                </c:choose>                                    
                            </tr>
                            </c:forEach>
			</tbody>
		</table>
		<div><div> <a class="button" href="/api/add-names">Add Name</a></div>
<%@ include file="common/footer.jspf" %>

