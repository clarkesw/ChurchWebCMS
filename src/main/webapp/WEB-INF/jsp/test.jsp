<%@ include file="cms/common/header.jspf" %>
<h3>Tester</h3>
<br>
    <c:if test="${not empty services}">

       <c:forEach items="${services}" var="timee" varStatus="counter">
           ${timee}
       </c:forEach>
    </c:if> 
    
<%@ include file="cms/common/footer.jspf" %>