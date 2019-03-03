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
                                    <th></th>
                                    <th></th>
				</tr>
			</thead>
			<tbody>

                                <tr>
                                    <td>${info.name}</td>
                                    <td>${info.missionStatement}</td>
                                    <td>${info.leadPastor}</td>
                                    <td><a type="button" class="btn btn-success" 
                                          href="addAddressForChurch?address_id=${info.address.id}">
                                           Edit Address</a></td>
                                    <td><a type="button" class="btn btn-success" href="/update-info">Update</a></td>
                                </tr>

			</tbody>
		</table>
		<div> 
<%@ include file="common/footer.jspf" %>

