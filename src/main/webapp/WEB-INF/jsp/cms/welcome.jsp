<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h1>${user}'s Dashboard!! </h1>
        <div>
            <h3>
                <table>
                
                    <tr>
                        <td>No Messages</td>
                        <td></td>
                    </tr>
                
                </table>
            </h3>
        </div>
</div>
 
-<a>File Upload:</a>
Select file: <br />
<form action="fileupload.jsp" method="post"
                        enctype="multipart/form-data">
<input type="file" name="file" size="50" />
<br />
<input type="submit" value="Upload File" />
</form> 

<%@ include file="common/footer.jspf" %>
