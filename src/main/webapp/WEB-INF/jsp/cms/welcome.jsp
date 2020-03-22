<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h1>${user}'s Dashboard!! </h1>
        <div>
            <h3>
                <table>
                
                    <tr>
                        <td><%--No Messages --%></td>
                        <td></td>
                    </tr>
                
                </table>
            </h3>
        </div>
        <br>
        <div>
            <table class="welcome welcome-tr" >
                <caption>Prayer Duty:</caption>
                    <tbody>
                        <c:if test="${empty staffers}">
                                <tr>
                                    <td><b>Nobody</b></td>
                                </tr>
                        </c:if>
                        <c:forEach items="${staffers}" var="staff">
                            <tr class="welcome welcome-tr">
                                <td class="welcome welcome-tr">${staff.fullName}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
            </table>
        </div>
        <div class="footer">
            <a>File Upload:</a>
            Select file: <br />
            <form action="fileupload.jsp" method="post"
                                    enctype="multipart/form-data">
            <input type="file" name="file" size="50" />
            <br />
            <input type="submit" value="Upload File" />
            </form> 
        </div>
</div>
 


<%@ include file="common/footer.jspf" %>
