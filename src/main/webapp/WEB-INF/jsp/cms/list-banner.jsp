<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Banner</caption>
			<thead>
				<tr>
                                    <th>Message</th>
                                    <th>Last Modified Date</th>
				</tr>
			</thead>
			<tbody>
                                <tr>
                                    <td>${banner.message}</td>                                                                                     
                                    <td>${banner.lastModified}</td>
                                        <c:choose>
                                            <c:when test="${empty banner.message}">
                                                <td><a type="button" class="btn btn-success" 
                                                      href="/api/add-banner">Add</a></td>
                                            </c:when>
                                            <c:otherwise>
                                                 <td><a type="button" class="btn btn-success" 
                                                      href="/api/add-banner">Edit</a></td>                                       
                                            </c:otherwise>
                                        </c:choose> 
                                                          
                                    <td><a type="button" class="btn btn-warning" href="/api/delete-banner">Delete</a></td>
                                </tr>
			</tbody>
		</table>
<%@ include file="common/footer.jspf" %>

