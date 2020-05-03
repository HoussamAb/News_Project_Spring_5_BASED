<%--
  Created by IntelliJ IDEA.
  User: binizmohamed
  Date: 4/6/20
  Time: 14:52
  To change this template use File | Settings | File Templates.
--%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Blog</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <jsp:directive.include file="layout/header.jsp" />
 <header class="col-lg-12">
    <h1>Tous les tags du Blog</h1>

        <div class="col-lg-12">
            <a href="${pageContext.request.contextPath}/auth/login" class="btn btn-primary">
                Login </a>
            <a href="${pageContext.request.contextPath}/auth/register" class="btn btn-primary">
                Sign Up </a>
            <br/>
        </div>

     <c:forEach items="${articles}" var="item">
         <div style="border-style: solid;">
             <div>${item.id}</div><div>${item.title}</div><div>${item.body}</div>
             <div>
                 <a href="${pageContext.request.contextPath}/delete1/${item.id}" class="btn btn-danger"
                    onclick="if (!(confirm('Vous Voulez supprimer cet element?'))) return false">Supprimer</a>
                 <a href="${pageContext.request.contextPath}/article/add/${item.id}" class="btn btn-success">Modifier</a>
             </div>
         </div>

     </c:forEach>

 </header>

</div>

</body>
</html>