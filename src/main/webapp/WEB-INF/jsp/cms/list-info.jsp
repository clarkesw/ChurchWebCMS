<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
		<table class="table table-striped">
			<caption>Church Info</caption>
			<thead>
				<tr>
                                    <th>Name</th>
                                    <th>Mission Statement</th>
                                    <th>Lead Pastor</th>
                                    <th>Address</th>
                                    <th>Service Times</th>
                                    <th></th>
				</tr>
			</thead>
			<tbody>

                                <tr>
                                    <td>${info.name}</td>
                                    <td>${info.missionStatement}</td>
                                    <td>${info.leadPastor.fullName}</td>
                                    <c:choose>
                                        <c:when test="${empty info.address}">
                                            <td><a type="button" class="btn btn-success" 
                                                  href="addAddressForChurch?address_id=-1">Add Address</a></td>
                                        </c:when>
                                        <c:otherwise>
                                             <td><a type="button" class="btn btn-success" 
                                                  href="addAddressForChurch?address_id=${info.address.id}">Edit Address</a></td>                                       
                                        </c:otherwise>
                                    </c:choose>
                                    <td><a type="button" class="btn btn-success" 
                                                  href="editServiceTimes">Edit Times</a></td>
                                    <c:choose>
                                        <c:when test="${empty info.id}">
                                            <td><a type="button" class="btn btn-success" 
                                                  href="update-info?id=-1">Add</a></td>
                                        </c:when>
                                        <c:otherwise>
                                             <td><a type="button" class="btn btn-success" 
                                                  href="update-info?id=${info.id}">Update</a></td>                                       
                                        </c:otherwise>
                                    </c:choose>
                                </tr>

			</tbody>
		</table>
		
<%@ include file="common/footer.jspf" %>

