<%--
  Created by IntelliJ IDEA.
  User: Houssam
  Date: 26/04/2020
  Time: 00:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><html>
<head>
    <title>register</title>
    <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" />
    <script src="<c:url value="/resources/js/jquery-1.11.1.min.js" />"></script>
    <script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
</head>
<body>
<div class="container">
    <jsp:directive.include file="../layout/header.jsp" />
    <header class="col-lg-4">
        <h1>Connexion</h1>
        <form:form method="post" action="${pageContext.request.contextPath}/auth/register" modelAttribute="userForm">
            <div class="form-group">
                <label for="username">Username</label>
                <form:input path="username" cssClass="form-control"  placeholder="username" />
                <form:errors path="username" cssClass="alert-danger" />
            </div>
            <div class="form-group">
                <label for="password">Password</label>
                <form:input path="password" cssClass="form-control"  placeholder="password" />
                <form:errors path="password" cssClass="alert-danger" />
            </div>
            <div class="form-group">
                <label for="email">email</label>
                <form:input path="email" cssClass="form-control"  placeholder="email" />
                <form:errors path="email" cssClass="alert-danger" />
            </div>
            <div class="form-check">
            <label class="form-check-label">
                Roles
            </label>


            <c:forEach items="${roles}"   var="roles">
                <c:choose>
                    <c:when test="${roles.name}">
                        <form:radiobutton path="roles" value="${roles.id}" label="${roles.name}" checked="checked" />
                    </c:when>
                    <c:otherwise>
                        <form:radiobutton path="roles" value="${roles.id}" label="${roles.name}" checked="checked" />
                    </c:otherwise>
                </c:choose>

            </c:forEach>
            <form:errors path="roles" cssClass="alert-danger" />
        </div>
            <input type="submit" value="Submit"/>
        </form:form>
    </header>
</div>
</html>