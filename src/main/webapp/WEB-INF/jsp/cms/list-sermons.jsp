<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
                    <table class="table table-striped">
                        <caption>Sermons</caption>
                        <thead>
		<tr>
                                    <th>Title</th>
                                    <th>Sub Title</th>
                                    <th>Date</th>
                                    <th><Passages</th>
                                    <th></th>
                                    <th></th>
                                    <th></th>
		</tr>
		</thead>
			<tbody>
                            <c:if test="${not empty sermons}">
                                <c:forEach items="${sermons}" var="sermon">
                                    <fmt:formatDate value="${sermon.sermonDate}" var="formattedDate" 
                                                type="date" pattern="MM-dd-yyyy" />
					<tr>
                                            <td>${sermon.title}</td>
                                            <td>${sermon.subTitle}</td>
                                            <td>${formattedDate}</td>
                                             <c:choose>
                                                <c:when test="${empty sermon.passages}">
                                                    <td><a type="button" class="btn btn-success" 
                                                          href="/api/addPassagesToSermon?id=${sermon.id}">Add Passages</a></td>
                                                </c:when>
                                                <c:otherwise>
                                                <%-- sermonId=${sermon.passages.id}& --%>      <td><a type="button" class="btn btn-success" 
                                                          href="/api/addPassagesToSermon?id=${sermon.id}">Edit Passages</a></td>                                     
                                                </c:otherwise>
                                            </c:choose> 
                                                          
                                             <c:choose>
                                                <c:when test="${empty sermon.description}">
                                                    <td><a type="button" class="btn btn-success" 
                                                          href="/api/addDescriptionToSermon?id=${sermon.id}">Add Description</a></td>
                                                </c:when>
                                                <c:otherwise>
                                                    <td><a type="button" class="btn btn-success" 
                                                          href="/api/addDescriptionToSermon?id=${sermon.id}">Edit Description</a></td>                                     
                                                </c:otherwise>
                                            </c:choose> 
                       
                                            <td><a type="button" class="btn btn-success" href="/api/update-sermon?id=${sermon.id}">Edit</a></td>
                                            <td><a type="button" class="btn btn-warning" href="/api/delete-sermon?id=${sermon.id}">Delete</a></td> 
					</tr>
                                    </c:forEach>
                            </c:if>
			</tbody>
		</table>
		<div> <a class="button" href="/api/add-sermons">Add Sermon</a></div>
<%@ include file="common/footer.jspf" %>

