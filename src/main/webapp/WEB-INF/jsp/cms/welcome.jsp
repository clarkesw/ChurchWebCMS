<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
   	Welcome ${sUser}!! 
        <br>
        <a href="/list-todos">Your Todo's</a>
        <br>
        <a href="/list-events">Manage Calendar Events</a>
</div>

<%@ include file="common/footer.jspf" %>
