<%--
  Created by IntelliJ IDEA.
  User: Houssam
  Date: 26/04/2020
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>index page</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
<c:forEach items="${session}"   var="users">
            <p><label>user :</label>"${users.key}" <p/>
            <p><label> role :</label> "${users.value}" <p/>
</c:forEach>
<a class="btn btn-default" href="${pageContext.request.contextPath}/auth/logout">deconnexion</a>
<a class="btn btn-default" href="${pageContext.request.contextPath}/article/add">ajouter un article</a>
</div>
</body>
</html>
