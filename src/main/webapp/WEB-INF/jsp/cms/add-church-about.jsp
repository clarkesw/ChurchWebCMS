<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <title>Church CMS</title>
        <script src="../../../../scripts/jquery-1.3.2.js"></script> 
        <script src="../webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="../../../../scripts/jquery.wysiwyg.js"></script>

        <link href="../webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="../../../../stylesheets/jquery.wysiwyg.css" type="text/css" />
        <link rel="stylesheet" href="../../../../stylesheets/examples.css" type="text/css" />
    </head>

<body>
<%@ include file="common/navigation.jspf" %>
	<div class="container">
            <form:form method="post" action="/api/updateChurchAbout" modelAttribute="info"> 
                <fieldset class="form-group">
                        <form:label path="about">About our Church</form:label> 
                        <form:textarea  name="wysiwyg" id="wysiwyg" rows="30" cols="150" type="text" 
                                class="form-control" path="about"/>
                        <form:errors path="about" cssClass="text-warning"/>
                </fieldset>
                        <form:hidden path="id"/> 
                        <form:hidden path="name"/>
                        <form:hidden path="missionStatement"/>
                        <form:hidden path="email"/>             
                        <form:hidden path="url"/>           
                        <form:hidden path="telephone"/>           
                        <form:hidden path="fax"/>           
                        <form:hidden path="facebook"/>           
                        <form:hidden path="twitter"/>           
                        <form:hidden path="youtube"/>           
                        <form:hidden path="address"/> 
                        <form:hidden path="staffers"/>    
                        <form:hidden path="serviceTimes"/>           
            <button type="submit" class="btn btn-success">Update</button>
            </form:form>
	</div>

<%@ include file="common/footer.jspf" %>
