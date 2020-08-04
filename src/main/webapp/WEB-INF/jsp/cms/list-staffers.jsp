<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Staff</caption>
			<thead>
				<tr>
                                    <th>First Name</th>
                                    <th>Last Name</th>
                                    <th>Position</th>
                                    <th></th>
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
                                    <c:choose>
                                        <c:when test="${(userEdit && staff.user.id > 0) || currentUser == staff.user.username}">
                                            <td><a type="button" class="btn btn-success" 
                                                  href="/api/update-user?userId=${staff.user.id}&staffId=${staff.id}">Edit User</a></td>                                         
                                        </c:when>
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:choose>
                                        <c:when test="${empty staff.homeAddress}">
                                            <td><a type="button" class="btn btn-success" 
                                                   href="/api/addAddressForStaff?fisrtName=${staff.firstName}&lastName=${staff.lastName}">
                                                    Add Address</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td><a type="button" class="btn btn-success" 
                                                   href="/api/addAddressForStaff?fisrtName=${staff.firstName}&lastName=${staff.lastName}">
                                                    Edit Address</a></td>
                                        </c:otherwise>                                            
                                    </c:choose>                                                 

                                    <td><a type="button" class="btn btn-success" href="/api/update-staff?id=${staff.id}">Update</a></td>
                                    <c:choose>
                                        <c:when test="${staff.position != 'Lead Pastor'}">
                                            <td><a type="button" class="btn btn-warning" href="/api/delete-staff?id=${staff.id}">Delete</a></td>
                                        </c:when>
                                        <c:otherwise>
                                            <td></td>
                                        </c:otherwise>                                            
                                    </c:choose>                                            
                                </tr>
                            </c:forEach>
			</tbody>
		</table>
		<div> <a class="button" href="/api/add-staff?isAdmin=0">Add Church Member</a></div>
                 <c:choose>
                    <c:when test="${userEdit}">
                        <div><a class="button" href="/api/add-staff?isAdmin=1">Add Website Admin</a></div> 
                    </c:when>
                </c:choose>
 
        </div>
<%@ include file="common/footer.jspf" %>

