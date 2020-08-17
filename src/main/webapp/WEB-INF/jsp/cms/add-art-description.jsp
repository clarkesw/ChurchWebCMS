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
            <form:form method="post" action="/api/addDescriptionToArticle" modelAttribute="article">
                <fieldset class="form-group">
                        <form:label path="content">Description</form:label> 
                        <form:textarea  name="wysiwyg" id="wysiwyg" rows="30" cols="150" type="text" 
                                class="form-control" path="content"/>
                        <form:errors path="content" cssClass="text-warning"/>                        
                </fieldset>
                        <form:hidden path="id"/> 
                        <form:hidden path="title"/>
                        <form:hidden path="subTitle"/>
                        <form:hidden path="imageURL"/>
            <button type="submit" class="btn btn-success">Update</button>
            </form:form>
	</div>

<%@ include file="common/footer.jspf" %>
